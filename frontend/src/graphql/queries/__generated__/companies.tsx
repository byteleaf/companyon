/* eslint-disable */
import * as Types from '../../types';

import { GqlFullCompanyFragment } from '../../fragments/__generated__/fullCompany';
import { gql } from '@apollo/client';
import { FullCompanyFragmentDoc } from '../../fragments/__generated__/fullCompany';
import * as Apollo from '@apollo/client';
export type GqlCompaniesQueryVariables = Types.Exact<{ [key: string]: never; }>;


export type GqlCompaniesQuery = (
  { __typename?: 'Query' }
  & { companies: Array<(
    { __typename?: 'Company' }
    & GqlFullCompanyFragment
  )> }
);


export const CompaniesDocument = gql`
    query companies {
  companies {
    ...fullCompany
  }
}
    ${FullCompanyFragmentDoc}`;

/**
 * __useCompaniesQuery__
 *
 * To run a query within a React component, call `useCompaniesQuery` and pass it any options that fit your needs.
 * When your component renders, `useCompaniesQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useCompaniesQuery({
 *   variables: {
 *   },
 * });
 */
export function useCompaniesQuery(baseOptions?: Apollo.QueryHookOptions<GqlCompaniesQuery, GqlCompaniesQueryVariables>) {
        return Apollo.useQuery<GqlCompaniesQuery, GqlCompaniesQueryVariables>(CompaniesDocument, baseOptions);
      }
export function useCompaniesLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GqlCompaniesQuery, GqlCompaniesQueryVariables>) {
          return Apollo.useLazyQuery<GqlCompaniesQuery, GqlCompaniesQueryVariables>(CompaniesDocument, baseOptions);
        }
export type CompaniesQueryHookResult = ReturnType<typeof useCompaniesQuery>;
export type CompaniesLazyQueryHookResult = ReturnType<typeof useCompaniesLazyQuery>;
export type CompaniesQueryResult = Apollo.QueryResult<GqlCompaniesQuery, GqlCompaniesQueryVariables>;