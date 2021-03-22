/* eslint-disable */
import * as Types from '../../types';

import { GqlFullUserFragment } from '../../fragments/__generated__/fullUser';
import { gql } from '@apollo/client';
import { FullUserFragmentDoc } from '../../fragments/__generated__/fullUser';
import * as Apollo from '@apollo/client';
export type GqlUserUpdateSubscriptionVariables = Types.Exact<{ [key: string]: never; }>;


export type GqlUserUpdateSubscription = (
  { __typename?: 'Subscription' }
  & { userUpdate: (
    { __typename?: 'UserUpdate' }
    & Pick<Types.GqlUserUpdate, 'type'>
    & { entity: (
      { __typename?: 'User' }
      & GqlFullUserFragment
    ) }
  ) }
);


export const UserUpdateDocument = gql`
    subscription userUpdate {
  userUpdate {
    type
    entity {
      ...fullUser
    }
  }
}
    ${FullUserFragmentDoc}`;

/**
 * __useUserUpdateSubscription__
 *
 * To run a query within a React component, call `useUserUpdateSubscription` and pass it any options that fit your needs.
 * When your component renders, `useUserUpdateSubscription` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the subscription, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useUserUpdateSubscription({
 *   variables: {
 *   },
 * });
 */
export function useUserUpdateSubscription(baseOptions?: Apollo.SubscriptionHookOptions<GqlUserUpdateSubscription, GqlUserUpdateSubscriptionVariables>) {
        return Apollo.useSubscription<GqlUserUpdateSubscription, GqlUserUpdateSubscriptionVariables>(UserUpdateDocument, baseOptions);
      }
export type UserUpdateSubscriptionHookResult = ReturnType<typeof useUserUpdateSubscription>;
export type UserUpdateSubscriptionResult = Apollo.SubscriptionResult<GqlUserUpdateSubscription>;