/* eslint-disable */
import * as Types from '../../types';

import { GqlFullUserFragment } from '../../fragments/__generated__/fullUser';
import { gql } from '@apollo/client';
import { FullUserFragmentDoc } from '../../fragments/__generated__/fullUser';
import * as Apollo from '@apollo/client';
export type GqlUserQueryVariables = Types.Exact<{
  id: Types.Scalars['ID'];
}>;


export type GqlUserQuery = (
  { __typename?: 'Query' }
  & { user: (
    { __typename?: 'User' }
    & GqlFullUserFragment
  ) }
);


export const UserDocument = gql`
    query user($id: ID!) {
  user(id: $id) {
    ...fullUser
  }
}
    ${FullUserFragmentDoc}`;

/**
 * __useUserQuery__
 *
 * To run a query within a React component, call `useUserQuery` and pass it any options that fit your needs.
 * When your component renders, `useUserQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useUserQuery({
 *   variables: {
 *      id: // value for 'id'
 *   },
 * });
 */
export function useUserQuery(baseOptions: Apollo.QueryHookOptions<GqlUserQuery, GqlUserQueryVariables>) {
        return Apollo.useQuery<GqlUserQuery, GqlUserQueryVariables>(UserDocument, baseOptions);
      }
export function useUserLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GqlUserQuery, GqlUserQueryVariables>) {
          return Apollo.useLazyQuery<GqlUserQuery, GqlUserQueryVariables>(UserDocument, baseOptions);
        }
export type UserQueryHookResult = ReturnType<typeof useUserQuery>;
export type UserLazyQueryHookResult = ReturnType<typeof useUserLazyQuery>;
export type UserQueryResult = Apollo.QueryResult<GqlUserQuery, GqlUserQueryVariables>;