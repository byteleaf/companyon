import React, { Suspense } from 'react';
import { ApolloProvider } from '@apollo/client';
import ReactDOM from 'react-dom';

import './styles.css';
import Routes from './routing/Routes';
import { createApolloClient } from './graphql/apolloClient';
import { AuthProvider } from './context/AuthContext';
import { EnvVariables } from './helpers/env';

import './i18n';

const { URI, WS_URI, isDevelopment } = EnvVariables;
export const { apolloClient, wsClient } = createApolloClient(URI, WS_URI, isDevelopment);

const render = (Component: React.FC) => {
  ReactDOM.render(
    <React.StrictMode>
      <AuthProvider apolloClient={apolloClient} wsClient={wsClient}>
        <ApolloProvider client={apolloClient}>
          <Suspense fallback={<div>Loading...</div>}>
            <Component />
          </Suspense>
        </ApolloProvider>
      </AuthProvider>
    </React.StrictMode>,
    document.getElementById('root'),
  );
};

render(Routes);

if (isDevelopment && module.hot) {
  module.hot.accept('./routing/Routes', () => {
    render(Routes);
  });
}
