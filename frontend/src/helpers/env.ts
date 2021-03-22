const isDevelopment = process.env.NODE_ENV === 'development';

export const EnvVariables = {
  // Environment
  isDevelopment,

  // Graphql
  URI: process.env.REACT_APP_BACKEND_URL || `${window.location.origin}/graphql`,
  WS_URI: process.env.REACT_APP_BACKEND_WS_URL || `${window.location.origin.replace(/^http/, 'ws')}/subscriptions`,

  // Auth0 Authentication
  AUTH_DOMAIN: process.env.REACT_APP_AUTH_DOMAIN || '',
  AUTH_AUDIENCE: process.env.REACT_APP_AUTH_AUDIENCE || '',
  AUTH_CLIENTID: process.env.REACT_APP_AUTH_CLIENT_ID || '',
};
