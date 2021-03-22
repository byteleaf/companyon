import React from 'react';
import MockedRouter from '../../../../helpers/test/MockedRouter';
import Link from './Link';

export default {
  component: Link,
  title: 'Atoms/Link',
};

export const Primary = () => {
  return (
    <MockedRouter>
      <Link to="/">Click me!</Link>
    </MockedRouter>
  );
};

export const Inline = () => {
  return (
    <MockedRouter>
      I am an inline Link, so{' '}
      <Link to="/" inline>
        click me
      </Link>{' '}
      please!
    </MockedRouter>
  );
};
