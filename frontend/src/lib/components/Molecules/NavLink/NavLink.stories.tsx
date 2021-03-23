import React from 'react';
import { backgrounds } from '../../../../../.storybook/preview';
import MockedRouter from '../../../../helpers/test/MockedRouter';
import NavLink from './NavLink';

export default {
  component: NavLink,
  title: 'Molecules/Navigation/Nav Link',
  parameters: {
    backgrounds: { ...backgrounds, default: 'dark' },
  },
};

export const Inactive = () => (
  <MockedRouter>
    <NavLink href="/asd" icon="Home">
      Dashboard
    </NavLink>
  </MockedRouter>
);

export const Active = () => (
  <MockedRouter>
    <NavLink href="/" icon="Home">
      Dashboard
    </NavLink>
  </MockedRouter>
);
