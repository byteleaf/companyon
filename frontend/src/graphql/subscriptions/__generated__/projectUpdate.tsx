/* eslint-disable */
import * as Types from '../../types';

import { GqlFullProjectFragment } from '../../fragments/__generated__/fullProject';
import { gql } from '@apollo/client';
import { FullProjectFragmentDoc } from '../../fragments/__generated__/fullProject';
import * as Apollo from '@apollo/client';
export type GqlProjectUpdateSubscriptionVariables = Types.Exact<{ [key: string]: never; }>;


export type GqlProjectUpdateSubscription = (
  { __typename?: 'Subscription' }
  & { projectUpdate: (
    { __typename?: 'ProjectUpdate' }
    & Pick<Types.GqlProjectUpdate, 'type'>
    & { entity: (
      { __typename?: 'Project' }
      & GqlFullProjectFragment
    ) }
  ) }
);


export const ProjectUpdateDocument = gql`
    subscription projectUpdate {
  projectUpdate {
    type
    entity {
      ...fullProject
    }
  }
}
    ${FullProjectFragmentDoc}`;

/**
 * __useProjectUpdateSubscription__
 *
 * To run a query within a React component, call `useProjectUpdateSubscription` and pass it any options that fit your needs.
 * When your component renders, `useProjectUpdateSubscription` returns an object from Apollo Client that contains loading, error, and data properties
 * you can use to render your UI.
 *
 * @param baseOptions options that will be passed into the subscription, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options;
 *
 * @example
 * const { data, loading, error } = useProjectUpdateSubscription({
 *   variables: {
 *   },
 * });
 */
export function useProjectUpdateSubscription(baseOptions?: Apollo.SubscriptionHookOptions<GqlProjectUpdateSubscription, GqlProjectUpdateSubscriptionVariables>) {
        return Apollo.useSubscription<GqlProjectUpdateSubscription, GqlProjectUpdateSubscriptionVariables>(ProjectUpdateDocument, baseOptions);
      }
export type ProjectUpdateSubscriptionHookResult = ReturnType<typeof useProjectUpdateSubscription>;
export type ProjectUpdateSubscriptionResult = Apollo.SubscriptionResult<GqlProjectUpdateSubscription>;