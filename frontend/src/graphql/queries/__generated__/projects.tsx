/* eslint-disable */
import * as Types from '../../types';

import { GqlFullProjectFragment } from '../../fragments/__generated__/fullProject';
import { gql } from '@apollo/client';
import { FullProjectFragmentDoc } from '../../fragments/__generated__/fullProject';
import * as Apollo from '@apollo/client';
export type GqlProjectsQueryVariables = Types.Exact<{ [key: string]: never; }>;


export type GqlProjectsQuery = (
  { __typename?: 'Query' }
  & { projects: Array<(
    { __typename?: 'Project' }
    & GqlFullProjectFragment
  )> }
);


export const ProjectsDocument = gql`
    query projects {
  projects {
    ...fullProject
  }
}
    ${FullProjectFragmentDoc}`;

/**
 * __useProjectsQuery__
 *
 * To run a query within a React component, call `useProjectsQuery` and pass it any options that fit your needs.
 * When your component renders, `useProjectsQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useProjectsQuery({
 *   variables: {
 *   },
 * });
 */
export function useProjectsQuery(baseOptions?: Apollo.QueryHookOptions<GqlProjectsQuery, GqlProjectsQueryVariables>) {
        return Apollo.useQuery<GqlProjectsQuery, GqlProjectsQueryVariables>(ProjectsDocument, baseOptions);
      }
export function useProjectsLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GqlProjectsQuery, GqlProjectsQueryVariables>) {
          return Apollo.useLazyQuery<GqlProjectsQuery, GqlProjectsQueryVariables>(ProjectsDocument, baseOptions);
        }
export type ProjectsQueryHookResult = ReturnType<typeof useProjectsQuery>;
export type ProjectsLazyQueryHookResult = ReturnType<typeof useProjectsLazyQuery>;
export type ProjectsQueryResult = Apollo.QueryResult<GqlProjectsQuery, GqlProjectsQueryVariables>;