/* eslint-disable */
import * as Types from '../../types';

import { GqlFullTimeLogFragment } from '../../fragments/__generated__/fullTimeLog';
import { gql } from '@apollo/client';
import { FullTimeLogFragmentDoc } from '../../fragments/__generated__/fullTimeLog';
import * as Apollo from '@apollo/client';
export type GqlTimeLogUpdateSubscriptionVariables = Types.Exact<{ [key: string]: never; }>;


export type GqlTimeLogUpdateSubscription = (
  { __typename?: 'Subscription' }
  & { timeLogUpdate: (
    { __typename?: 'TimeLogUpdate' }
    & Pick<Types.GqlTimeLogUpdate, 'type'>
    & { entity: (
      { __typename?: 'TimeLog' }
      & GqlFullTimeLogFragment
    ) }
  ) }
);


export const TimeLogUpdateDocument = gql`
    subscription timeLogUpdate {
  timeLogUpdate {
    type
    entity {
      ...fullTimeLog
    }
  }
}
    ${FullTimeLogFragmentDoc}`;

/**
 * __useTimeLogUpdateSubscription__
 *
 * To run a query within a React component, call `useTimeLogUpdateSubscription` and pass it any options that fit your needs.
 * When your component renders, `useTimeLogUpdateSubscription` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the subscription, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useTimeLogUpdateSubscription({
 *   variables: {
 *   },
 * });
 */
export function useTimeLogUpdateSubscription(baseOptions?: Apollo.SubscriptionHookOptions<GqlTimeLogUpdateSubscription, GqlTimeLogUpdateSubscriptionVariables>) {
        return Apollo.useSubscription<GqlTimeLogUpdateSubscription, GqlTimeLogUpdateSubscriptionVariables>(TimeLogUpdateDocument, baseOptions);
      }
export type TimeLogUpdateSubscriptionHookResult = ReturnType<typeof useTimeLogUpdateSubscription>;
export type TimeLogUpdateSubscriptionResult = Apollo.SubscriptionResult<GqlTimeLogUpdateSubscription>;