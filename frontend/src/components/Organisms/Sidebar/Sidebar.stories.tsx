import React from 'react';
import { action } from '@storybook/addon-actions';
import { MockedProvider } from '@apollo/client/testing';
import MockedRouter from '../../../helpers/test/MockedRouter';
import Sidebar from './Sidebar';
import currentUser from '../../../graphql/queries/currentUser';
import { MockedAuthProvider } from '../../../context/AuthContext';

export default {
  component: Sidebar,
  title: 'Organisms/Navigation/Sidebar',
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

export const Default = () => (
  <MockedAuthProvider logoutCallback={action('logout')}>
    <MockedProvider mocks={mocks}>
      <MockedRouter>
        <Sidebar menuActive={false} setMenuActive={action('Open Menu')} />
      </MockedRouter>
    </MockedProvider>
  </MockedAuthProvider>
);

export const MobileActive = () => (
  <MockedAuthProvider logoutCallback={action('logout')}>
    <MockedProvider mocks={mocks}>
      <MockedRouter>
        <Sidebar menuActive setMenuActive={action('Close Menu')} />
      </MockedRouter>
    </MockedProvider>
  </MockedAuthProvider>
);
