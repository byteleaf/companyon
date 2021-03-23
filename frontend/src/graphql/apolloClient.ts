import { ApolloClient, createHttpLink, InMemoryCache, ServerError, split, from } from '@apollo/client';
import { setContext } from '@apollo/client/link/context';
import { onError } from '@apollo/client/link/error';
import { WebSocketLink } from '@apollo/client/link/ws';
import { getMainDefinition } from '@apollo/client/utilities';
import { SubscriptionClient } from 'subscriptions-transport-ws';
import possibleTypes from './fragmentTypes.json';

export const createApolloClient = (httpUri: string, wsUri: string, development: boolean) => {
  const httpLink = createHttpLink({
    uri: httpUri,
    credentials: 'same-origin',
  });

  const authLink = setContext((_, { headers }) => {
    const token = localStorage.getItem('token');

    return {
      headers: {
        ...headers,
        Authorization: token ? `Bearer ${token}` : '',
      },
    };
  });

  const wsClient = new SubscriptionClient(wsUri, {
    lazy: true,
    reconnect: true,
  });

  const wsLink = () => new WebSocketLink(wsClient);

  const errorLink = onError(({ graphQLErrors, networkError }) => {
    if (graphQLErrors)
      graphQLErrors.forEach(({ message, locations, path }) => {
        if (development) {
          // eslint-disable-next-line no-console
          console.log(`[GraphQL error]: Message: ${message}, Location: ${locations}, Path: ${path}`);
        }
      });
    if (networkError) {
      // eslint-disable-next-line no-console
      if (development) console.log(networkError);

      if (!Object.keys(networkError).includes('statusCode')) return;

      const { statusCode } = networkError as ServerError;

      if (statusCode === 401) {
        localStorage.removeItem('token');
        window.location.href = '/login';
      }
    }
  });

  const link = split(
    // split based on operation type
    ({ query }) => {
      const definition = getMainDefinition(query);
      return definition.kind === 'OperationDefinition' && definition.operation === 'subscription';
    },
    wsLink(),
    from([errorLink, authLink, httpLink]),
  );

  const apolloClient = new ApolloClient({
    link,
    cache: new InMemoryCache(possibleTypes),
    defaultOptions: {
      query: {
        fetchPolicy: 'cache-first',
      },
    },
  });

  return { apolloClient, wsClient };
};
