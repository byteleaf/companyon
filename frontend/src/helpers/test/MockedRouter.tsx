import * as React from 'react';
import { createMemoryHistory, History } from 'history';
import { Router } from 'react-router-dom';

const MockedRouter: React.FC<{ history?: History; route?: string }> = ({
  children,
  route = '/',
  history = createMemoryHistory({ initialEntries: [route] }),
}) => {
  return <Router history={history}>{children}</Router>;
};

export default MockedRouter;
