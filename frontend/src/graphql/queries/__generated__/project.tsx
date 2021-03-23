/* eslint-disable */
import * as Types from '../../types';

import { GqlFullProjectFragment } from '../../fragments/__generated__/fullProject';
import { gql } from '@apollo/client';
import { FullProjectFragmentDoc } from '../../fragments/__generated__/fullProject';
import * as Apollo from '@apollo/client';
export type GqlProjectQueryVariables = Types.Exact<{
  id: Types.Scalars['ID'];
}>;


export type GqlProjectQuery = (
  { __typename?: 'Query' }
  & { project: (
    { __typename?: 'Project' }
    & GqlFullProjectFragment
  ) }
);


export const ProjectDocument = gql`
    query project($id: ID!) {
  project(id: $id) {
    ...fullProject
  }
}
    ${FullProjectFragmentDoc}`;

/**
 * __useProjectQuery__
 *
 * To run a query within a React component, call `useProjectQuery` and pass it any options that fit your needs.
 * When your component renders, `useProjectQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useProjectQuery({
 *   variables: {
 *      id: // value for 'id'
 *   },
 * });
 */
export function useProjectQuery(baseOptions: Apollo.QueryHookOptions<GqlProjectQuery, GqlProjectQueryVariables>) {
        return Apollo.useQuery<GqlProjectQuery, GqlProjectQueryVariables>(ProjectDocument, baseOptions);
      }
export function useProjectLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GqlProjectQuery, GqlProjectQueryVariables>) {
          return Apollo.useLazyQuery<GqlProjectQuery, GqlProjectQueryVariables>(ProjectDocument, baseOptions);
        }
export type ProjectQueryHookResult = ReturnType<typeof useProjectQuery>;
export type ProjectLazyQueryHookResult = ReturnType<typeof useProjectLazyQuery>;
export type ProjectQueryResult = Apollo.QueryResult<GqlProjectQuery, GqlProjectQueryVariables>;