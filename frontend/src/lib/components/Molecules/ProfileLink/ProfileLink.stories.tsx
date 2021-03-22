import { action } from '@storybook/addon-actions';
import React from 'react';
import { MockedAuthProvider } from '../../../../context/AuthContext';
import { GqlFullUserFragment } from '../../../../graphql/fragments/__generated__/fullUser';
import MockedRouter from '../../../../helpers/test/MockedRouter';
import ProfileLink from './ProfileLink';

export default {
  component: ProfileLink,
  title: 'Molecules/Navigation/Profile Link',
};

const currentUser: GqlFullUserFragment = {
  id: '1',
  firstName: 'Jeff',
  lastName: 'Bytezos',
  email: 'jeff@bytez.os',
  avatar: { url: '/img/testing_thumb.jpg' },
};

export const Default = () => (
  <MockedRouter>
    <MockedAuthProvider logoutCallback={action('logout')}>
      <ProfileLink loading={false} currentUser={currentUser} />
    </MockedAuthProvider>
  </MockedRouter>
);

export const Loading = () => (
  <MockedRouter>
    <ProfileLink loading />
  </MockedRouter>
);
