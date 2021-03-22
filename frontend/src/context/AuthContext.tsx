import React, { useContext, useEffect, useState } from 'react';
import { Auth0Provider, useAuth0 } from '@auth0/auth0-react';
import { ApolloClient, NormalizedCacheObject } from '@apollo/client';
import { SubscriptionClient } from 'subscriptions-transport-ws';
import { EnvVariables } from '../helpers/env';

const { AUTH_DOMAIN, AUTH_AUDIENCE, AUTH_CLIENTID } = EnvVariables;

const loginOptions = {
  scope: 'openid profile email',
  audience: AUTH_AUDIENCE,
};

interface AuthState {
  authenticated: boolean;
  loading: boolean;
  hasToken: boolean;
}

interface AuthActions {
  login: () => void;
  logout: () => void;
}

interface AuthProviderProps {
  apolloClient: ApolloClient<NormalizedCacheObject>;
  wsClient: SubscriptionClient;
}

const AuthStateContext = React.createContext<AuthState>({ authenticated: false, loading: true, hasToken: false });
const AuthDispatchContext = React.createContext<AuthActions>({
  login: () => {
    throw new Error('Auth Dispatch not set');
  },
  logout: () => {
    throw new Error('Auth Dispatch not set');
  },
});

const Contexts: React.FC<AuthProviderProps> = ({ children, apolloClient, wsClient }) => {
  const { isLoading, isAuthenticated, loginWithRedirect, logout, getAccessTokenSilently } = useAuth0();
  const [didFetchToken, setDidFetchToken] = useState(false);

  useEffect(() => {
    if (isLoading) {
      return;
    }

    async function fetchTokenSilently() {
      try {
        const token = await getAccessTokenSilently();
        localStorage.setItem('token', token);
        setDidFetchToken(true);
      } catch (e) {
        console.error('Error when silently fetching access token', e);
      }
    }

    if (isAuthenticated) {
      fetchTokenSilently();
    }
  }, [getAccessTokenSilently, isAuthenticated, isLoading]);

  const actions: AuthActions = {
    login: () => loginWithRedirect({ ...loginOptions }),
    logout: () => {
      logout({ returnTo: `${window.location.origin}/login` });
      localStorage.removeItem('token');
      apolloClient.clearStore();

      wsClient.unsubscribeAll();
      wsClient.close();
    },
  };

  return (
    <AuthStateContext.Provider value={{ authenticated: isAuthenticated, loading: isLoading, hasToken: didFetchToken }}>
      <AuthDispatchContext.Provider value={actions}>{children}</AuthDispatchContext.Provider>
    </AuthStateContext.Provider>
  );
};

export const AuthProvider: React.FC<AuthProviderProps> = ({ children, apolloClient, wsClient }) => {
  return (
    <Auth0Provider
      domain={AUTH_DOMAIN}
      clientId={AUTH_CLIENTID}
      redirectUri={`${window.location.origin}/login`}
      audience={AUTH_AUDIENCE}
      useRefreshTokens
    >
      <Contexts apolloClient={apolloClient} wsClient={wsClient}>
        {children}
      </Contexts>
    </Auth0Provider>
  );
};

export const useAuthState = () => {
  const context = useContext(AuthStateContext);
  if (context === undefined) {
    throw new Error('useAuthState must be used within a AuthProvider');
  }

  return context;
};

export const useAuthDispatch = () => {
  const context = useContext(AuthDispatchContext);
  if (context === undefined) {
    throw new Error('useAuthDispatch must be used within a AuthProvider');
  }

  return context;
};

export const MockedAuthProvider: React.FC<{
  value?: AuthState['authenticated'];
  loadingValue?: AuthState['loading'];
  loginCallback?: () => void;
  logoutCallback?: () => void;
}> = ({ children, value = true, loadingValue = false, loginCallback, logoutCallback }) => {
  const mockedAuthActions: AuthActions = {
    login: () => {
      if (loginCallback) loginCallback();
    },
    logout: () => {
      if (logoutCallback) logoutCallback();
    },
  };

  return (
    <AuthStateContext.Provider value={{ authenticated: value, loading: loadingValue, hasToken: false }}>
      <AuthDispatchContext.Provider value={mockedAuthActions}>{children}</AuthDispatchContext.Provider>
    </AuthStateContext.Provider>
  );
};
