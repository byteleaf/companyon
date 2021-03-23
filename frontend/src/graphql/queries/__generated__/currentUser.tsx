/* eslint-disable */
import * as Types from '../../types';

import { GqlFullUserFragment } from '../../fragments/__generated__/fullUser';
import { gql } from '@apollo/client';
import { FullUserFragmentDoc } from '../../fragments/__generated__/fullUser';
import * as Apollo from '@apollo/client';
export type GqlCurrentUserQueryVariables = Types.Exact<{ [key: string]: never; }>;


export type GqlCurrentUserQuery = (
  { __typename?: 'Query' }
  & { currentUser: (
    { __typename?: 'User' }
    & GqlFullUserFragment
  ) }
);


export const CurrentUserDocument = gql`
    query currentUser {
  currentUser {
    ...fullUser
  }
}
    ${FullUserFragmentDoc}`;

/**
 * __useCurrentUserQuery__
 *
 * To run a query within a React component, call `useCurrentUserQuery` and pass it any options that fit your needs.
 * When your component renders, `useCurrentUserQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useCurrentUserQuery({
 *   variables: {
 *   },
 * });
 */
export function useCurrentUserQuery(baseOptions?: Apollo.QueryHookOptions<GqlCurrentUserQuery, GqlCurrentUserQueryVariables>) {
        return Apollo.useQuery<GqlCurrentUserQuery, GqlCurrentUserQueryVariables>(CurrentUserDocument, baseOptions);
      }
export function useCurrentUserLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GqlCurrentUserQuery, GqlCurrentUserQueryVariables>) {
          return Apollo.useLazyQuery<GqlCurrentUserQuery, GqlCurrentUserQueryVariables>(CurrentUserDocument, baseOptions);
        }
export type CurrentUserQueryHookResult = ReturnType<typeof useCurrentUserQuery>;
export type CurrentUserLazyQueryHookResult = ReturnType<typeof useCurrentUserLazyQuery>;
export type CurrentUserQueryResult = Apollo.QueryResult<GqlCurrentUserQuery, GqlCurrentUserQueryVariables>;