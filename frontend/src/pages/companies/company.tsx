import React from 'react';
import { useParams } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import SidebarLayout from '../../lib/components/Organisms/Layouts/SidebarLayout';
import DetailView from '../../lib/components/Organisms/DetailView/DetailView';
import { useCompanyQuery } from '../../graphql/queries/__generated__/company';

const Loading = () => <span>Loading...</span>;

const Company = () => {
  const { t } = useTranslation();

  const { id } = useParams<{ id: string }>();
  const { loading, data } = useCompanyQuery({
    variables: { id: id as string },
    fetchPolicy: 'network-only',
  });

  let Inner: JSX.Element | null = null;

  if (loading) {
    Inner = <Loading />;
  } else if (data?.company) {
    const detailViewConfig = {
      header: {
        title: data.company.name,
        subtitle: data.company.id,
      },
      rows: [
        { key: 'key1', value: 'value1' },
        { key: 'key2', value: 'value2' },
        { key: 'key3', value: 'value3' },
        { key: 'key4', value: 'value4' },
      ],
    };

    Inner = <DetailView config={detailViewConfig} />;
  }

  return <SidebarLayout title={t('pages.companies.company.title')}>{Inner}</SidebarLayout>;
};

export default Company;
