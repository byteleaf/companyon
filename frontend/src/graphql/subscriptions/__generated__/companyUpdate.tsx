/* eslint-disable */
import * as Types from '../../types';

import { GqlFullCompanyFragment } from '../../fragments/__generated__/fullCompany';
import { gql } from '@apollo/client';
import { FullCompanyFragmentDoc } from '../../fragments/__generated__/fullCompany';
import * as Apollo from '@apollo/client';
export type GqlCompanyUpdateSubscriptionVariables = Types.Exact<{ [key: string]: never; }>;


export type GqlCompanyUpdateSubscription = (
  { __typename?: 'Subscription' }
  & { companyUpdate: (
    { __typename?: 'CompanyUpdate' }
    & Pick<Types.GqlCompanyUpdate, 'type'>
    & { entity: (
      { __typename?: 'Company' }
      & GqlFullCompanyFragment
    ) }
  ) }
);


export const CompanyUpdateDocument = gql`
    subscription companyUpdate {
  companyUpdate {
    type
    entity {
      ...fullCompany
    }
  }
}
    ${FullCompanyFragmentDoc}`;

/**
 * __useCompanyUpdateSubscription__
 *
 * To run a query within a React component, call `useCompanyUpdateSubscription` and pass it any options that fit your needs.
 * When your component renders, `useCompanyUpdateSubscription` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the subscription, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useCompanyUpdateSubscription({
 *   variables: {
 *   },
 * });
 */
export function useCompanyUpdateSubscription(baseOptions?: Apollo.SubscriptionHookOptions<GqlCompanyUpdateSubscription, GqlCompanyUpdateSubscriptionVariables>) {
        return Apollo.useSubscription<GqlCompanyUpdateSubscription, GqlCompanyUpdateSubscriptionVariables>(CompanyUpdateDocument, baseOptions);
      }
export type CompanyUpdateSubscriptionHookResult = ReturnType<typeof useCompanyUpdateSubscription>;
export type CompanyUpdateSubscriptionResult = Apollo.SubscriptionResult<GqlCompanyUpdateSubscription>;