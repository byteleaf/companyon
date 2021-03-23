import React from 'react';
import { useParams } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import SidebarLayout from '../../lib/components/Organisms/Layouts/SidebarLayout';
import DetailView from '../../lib/components/Organisms/DetailView/DetailView';
import { useUserQuery } from '../../graphql/queries/__generated__/user';
import EditableField from '../../lib/components/Organisms/EditableField/EditableField';
import { useUpdateUserMutation } from '../../graphql/mutations/__generated__/updateUser';
import { useUser } from '../../hooks/useUser';

const Loading = () => <span>Loading...</span>;
const NoData = () => <span>Not found...</span>;

const User = () => {
  const { t } = useTranslation();

  const { isAdmin } = useUser();

  const { id } = useParams<{ id: string }>();
  const { loading, data, refetch } = useUserQuery({
    variables: { id },
    onError: (err) => console.error(err),
  });

  const [update, { loading: updateLoading }] = useUpdateUserMutation({ onCompleted: () => refetch() });

  const handleConfirm = (v: { [key: string]: string }) => {
    const user = data?.user;
    if (!user) return;

    const { id: userId, firstName, lastName, email, admin } = user;
    if (!userId) return;

    update({ variables: { id: userId, input: { firstName, lastName, email, admin, ...v } } });
  };

  const renderContent = () => {
    if (loading) {
      return <Loading />;
    }
    if (!data) {
      return <NoData />;
    }
    const detailViewConfig = {
      header: {
        title: `${data.user.firstName} ${data.user.lastName}`,
        subtitle: data.user.email,
      },
      rows: [
        {
          key: t('pages.team.user.id'),
          value: data.user.id,
        },
        ...(isAdmin
          ? [
              {
                key: t('pages.team.user.admin'),
                value: data.user.admin ? 'Yes' : 'No',
              },
            ]
          : []),
        {
          key: t('pages.team.user.firstName'),
          value: (
            <EditableField
              id="user-editable-firstname"
              field="firstName"
              value={data.user.firstName}
              onConfirm={handleConfirm}
              editDisabled={updateLoading}
            />
          ),
        },
        {
          key: t('pages.team.user.lastName'),
          value: (
            <EditableField
              id="user-editable-lastname"
              field="lastName"
              value={data.user.lastName}
              onConfirm={handleConfirm}
              editDisabled={updateLoading}
            />
          ),
        },
        {
          key: t('pages.team.user.email'),
          value: (
            <EditableField
              id="user-editable-email"
              field="email"
              value={data.user.email}
              onConfirm={handleConfirm}
              editDisabled={updateLoading}
            />
          ),
        },
      ],
    };

    return <DetailView config={detailViewConfig} />;
  };

  return <SidebarLayout title={t('pages.team.user.title')}>{renderContent()}</SidebarLayout>;
};

export default User;
