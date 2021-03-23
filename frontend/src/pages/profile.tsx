/* eslint-disable react/jsx-one-expression-per-line */
import React from 'react';
import { useTranslation } from 'react-i18next';
import Avatar from '../lib/components/Atoms/Avatar/Avatar';
import DetailView from '../lib/components/Organisms/DetailView/DetailView';
import SidebarLayout from '../lib/components/Organisms/Layouts/SidebarLayout';
import { useCurrentUserQuery } from '../graphql/queries/__generated__/currentUser';
import { GqlFullUserFragment } from '../graphql/fragments/__generated__/fullUser';

const Loading = () => <span>Loading...</span>;

const Profile = () => {
  const { t } = useTranslation();
  const { data, loading } = useCurrentUserQuery();

  function renderProfileDetails(user: GqlFullUserFragment) {
    return (
      <DetailView
        config={{
          header: {
            title: `${user.lastName}, ${user.firstName}`,
            subtitle: t('pages.profile.subtitle'),
            image: user.avatar ? <Avatar imgSrc={user.avatar.url} /> : undefined,
          },
          rows: [
            {
              key: t('pages.profile.firstName'),
              value: user.firstName,
            },
            {
              key: t('pages.profile.lastName'),
              value: user.lastName,
            },
            {
              key: t('pages.profile.email'),
              value: user.email,
            },
          ],
        }}
      />
    );
  }

  return (
    <SidebarLayout title={t('pages.profile.title')}>
      {loading || !data ? <Loading /> : renderProfileDetails(data.currentUser)}
    </SidebarLayout>
  );
};

export default Profile;
