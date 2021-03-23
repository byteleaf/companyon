import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { TFunction } from 'i18next';
import styled from 'styled-components';
import tw from 'twin.macro';
import SidebarLayout from '../lib/components/Organisms/Layouts/SidebarLayout';
import { TableConfig, TableRow } from '../lib/components/Organisms/DataTable/types';
import DataTable from '../lib/components/Organisms/DataTable/DataTable';
import CompanyForm from '../components/Organisms/AddCompanyBar/AddCompanyBar';
import { GqlCompaniesQuery, useCompaniesQuery } from '../graphql/queries/__generated__/companies';
import {
  CompanyUpdateDocument,
  GqlCompanyUpdateSubscription,
} from '../graphql/subscriptions/__generated__/companyUpdate';
import Button from '../lib/components/Atoms/Button/Button';
import Icon from '../lib/components/Atoms/Icons/Icon';

const MarginDataTable = styled(DataTable)`
  ${tw`mt-8`}
`;

const createEmptyTableConfig = (t: TFunction): TableConfig => ({
  rowActions: [
    {
      type: 'Link',
      src: 'companies',
      label: t('common.actions.view'),
    },
    {
      type: 'DeleteCompany',
      src: 'companies',
      label: t('common.actions.delete'),
    },
  ],
  headers: [
    {
      key: 'name',
      value: t('pages.companies.header.name') as string,
    },
  ],
});

const Companies = () => {
  const { t } = useTranslation();

  const [willAdd, setWillAdd] = useState<boolean>(false);

  const [tableConfig] = useState<TableConfig>(createEmptyTableConfig(t));

  const { data, subscribeToMore } = useCompaniesQuery();

  useEffect(() => {
    subscribeToMore<GqlCompanyUpdateSubscription>({
      document: CompanyUpdateDocument,
      updateQuery: (prev, { subscriptionData }): GqlCompaniesQuery => {
        if (!subscriptionData?.data?.companyUpdate?.entity) return prev;

        const { type, entity } = subscriptionData.data.companyUpdate;

        const filteredCompanies = prev.companies.filter((c) => c.id !== entity.id);

        switch (type) {
          case 'CREATED':
          case 'UPDATED':
            return { ...prev, companies: [...filteredCompanies, entity] };
          case 'DELETED':
            return { ...prev, companies: filteredCompanies };
          default:
            return prev;
        }
      },
    });
  }, [subscribeToMore]);

  const companyList = data?.companies || [];
  const tableData = {
    rows: companyList.map(
      (company): TableRow => ({
        id: company.id,
        cells: [{ key: 'name', value: company.name }],
      }),
    ),
  };

  return (
    <SidebarLayout
      title={t('pages.companies.title')}
      headerButton={
        <Button onClick={() => setWillAdd((p) => !p)}>
          <Icon icon={willAdd ? 'MinusCircle' : 'PlusCircle'} color="primary" colorCharacter="inverse" />
        </Button>
      }
    >
      {willAdd && <CompanyForm />}
      <MarginDataTable tableConfig={tableConfig} tableData={tableData} />
    </SidebarLayout>
  );
};

export default Companies;
