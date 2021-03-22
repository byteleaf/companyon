/* eslint-disable */
import * as Types from '../../types';

import { GqlFullCompanyFragment } from '../../fragments/__generated__/fullCompany';
import { gql } from '@apollo/client';
import { FullCompanyFragmentDoc } from '../../fragments/__generated__/fullCompany';
import * as Apollo from '@apollo/client';
export type GqlCompanyQueryVariables = Types.Exact<{
  id: Types.Scalars['ID'];
}>;


export type GqlCompanyQuery = (
  { __typename?: 'Query' }
  & { company: (
    { __typename?: 'Company' }
    & GqlFullCompanyFragment
  ) }
);


export const CompanyDocument = gql`
    query company($id: ID!) {
  company(id: $id) {
    ...fullCompany
  }
}
    ${FullCompanyFragmentDoc}`;

/**
 * __useCompanyQuery__
 *
 * To run a query within a React component, call `useCompanyQuery` and pass it any options that fit your needs.
 * When your component renders, `useCompanyQuery` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the query, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useCompanyQuery({
 *   variables: {
 *      id: // value for 'id'
 *   },
 * });
 */
export function useCompanyQuery(baseOptions: Apollo.QueryHookOptions<GqlCompanyQuery, GqlCompanyQueryVariables>) {
        return Apollo.useQuery<GqlCompanyQuery, GqlCompanyQueryVariables>(CompanyDocument, baseOptions);
      }
export function useCompanyLazyQuery(baseOptions?: Apollo.LazyQueryHookOptions<GqlCompanyQuery, GqlCompanyQueryVariables>) {
          return Apollo.useLazyQuery<GqlCompanyQuery, GqlCompanyQueryVariables>(CompanyDocument, baseOptions);
        }
export type CompanyQueryHookResult = ReturnType<typeof useCompanyQuery>;
export type CompanyLazyQueryHookResult = ReturnType<typeof useCompanyLazyQuery>;
export type CompanyQueryResult = Apollo.QueryResult<GqlCompanyQuery, GqlCompanyQueryVariables>;