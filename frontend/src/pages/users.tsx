import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { TFunction } from 'i18next';
import styled from 'styled-components';
import tw from 'twin.macro';
import SidebarLayout from '../lib/components/Organisms/Layouts/SidebarLayout';
import DataTable from '../lib/components/Organisms/DataTable/DataTable';
import { TableConfig } from '../lib/components/Organisms/DataTable/types';
import { GqlUsersQuery, useUsersQuery } from '../graphql/queries/__generated__/users';
import { Loading } from '../lib/components/Molecules/ProfileLink/ProfileLink.stories';
import { GqlUserUpdateSubscription, UserUpdateDocument } from '../graphql/subscriptions/__generated__/userUpdate';
import AddUserBar from '../components/Organisms/AddUserBar/AddUserBar';
import Button from '../lib/components/Atoms/Button/Button';
import Icon from '../lib/components/Atoms/Icons/Icon';

const MarginDataTable = styled(DataTable)`
  ${tw`mt-8`}
`;

const createEmptyTableConfig = (t: TFunction): TableConfig => ({
  rowActions: [
    {
      type: 'Link',
      src: 'users',
      label: t('common.actions.view'),
    },
    {
      type: 'DeleteUser',
      src: 'users',
      label: t('common.actions.delete'),
    },
  ],
  headers: [
    { key: 'name', value: t('pages.team.header.name') as string },
    { key: 'email', value: t('pages.team.header.email') as string },
  ],
});

const Team = () => {
  const { t } = useTranslation();

  const [willAdd, setWillAdd] = useState<boolean>(false);

  const [tableConfig] = React.useState<TableConfig>(createEmptyTableConfig(t));

  const { data, loading, subscribeToMore } = useUsersQuery();

  useEffect(() => {
    subscribeToMore<GqlUserUpdateSubscription>({
      document: UserUpdateDocument,
      updateQuery: (prev, { subscriptionData }): GqlUsersQuery => {
        if (!subscriptionData?.data?.userUpdate?.entity) return prev;

        const { type, entity } = subscriptionData.data.userUpdate;

        const filteredUsers = prev.users.filter((p) => p.id !== entity.id);

        switch (type) {
          case 'CREATED':
          case 'UPDATED':
            return { ...prev, users: [...filteredUsers, entity] };
          case 'DELETED':
            return { ...prev, users: filteredUsers };
          default:
            return prev;
        }
      },
    });
  }, [subscribeToMore]);

  const usersList = data?.users || [];
  const tableData = {
    rows: usersList.map((user) => ({
      id: user.id,
      cells: [
        {
          key: 'name',
          value: `${user.firstName} ${user.lastName}`,
        },
        { key: 'email', value: user.email },
      ],
    })),
  };

  return (
    <SidebarLayout
      title={t('pages.team.title')}
      headerButton={
        <Button onClick={() => setWillAdd((p) => !p)}>
          <Icon icon={willAdd ? 'MinusCircle' : 'PlusCircle'} color="primary" colorCharacter="inverse" />
        </Button>
      }
    >
      {loading ? (
        <Loading />
      ) : (
        <>
          {willAdd && <AddUserBar />}
          <MarginDataTable tableConfig={tableConfig} tableData={tableData} />
        </>
      )}
    </SidebarLayout>
  );
};

export default Team;
