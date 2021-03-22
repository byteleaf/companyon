import React from 'react';
import { MockedProvider } from '@apollo/client/testing';
import { action } from '@storybook/addon-actions';
import MockedRouter from '../../../../helpers/test/MockedRouter';
import SidebarLayout from './SidebarLayout';
import currentUser from '../../../../graphql/queries/currentUser';
import { MockedAuthProvider } from '../../../../context/AuthContext';

export default {
  component: SidebarLayout,
  title: 'Organisms/Layouts/Sidebar Layout',
};

const mocks = [
  {
    request: {
      query: currentUser,
    },
    result: {
      data: {
        currentUser: {
          id: '1',
          firstName: 'Jeff',
          lastName: 'Bytezos',
          email: 'jeff@bytez.os',
          admin: true,
          avatar: {
            id: '1',
            url: '/img/testing_thumb.jpg',
            mimeType: 'image/jpg',
          },
          __typename: 'User',
        },
      },
    },
  },
];

export const WithContent = () => (
  <MockedAuthProvider logoutCallback={action('logout')}>
    <MockedProvider mocks={mocks}>
      <MockedRouter>
        <SidebarLayout>
          <div>Hello</div>
        </SidebarLayout>
      </MockedRouter>
    </MockedProvider>
  </MockedAuthProvider>
);

export const WithoutContent = () => (
  <MockedAuthProvider logoutCallback={action('logout')}>
    <MockedProvider mocks={mocks}>
      <MockedRouter>
        <SidebarLayout />
      </MockedRouter>
    </MockedProvider>
  </MockedAuthProvider>
);
