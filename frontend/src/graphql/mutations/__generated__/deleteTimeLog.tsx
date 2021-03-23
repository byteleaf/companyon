/* eslint-disable */
import * as Types from '../../types';

import { GqlFullTimeLogFragment } from '../../fragments/__generated__/fullTimeLog';
import { gql } from '@apollo/client';
import { FullTimeLogFragmentDoc } from '../../fragments/__generated__/fullTimeLog';
import * as Apollo from '@apollo/client';
export type GqlDeleteTimeLogMutationVariables = Types.Exact<{
  id: Types.Scalars['ID'];
}>;


export type GqlDeleteTimeLogMutation = (
  { __typename?: 'Mutation' }
  & { deleteTimeLog: (
    { __typename?: 'TimeLog' }
    & GqlFullTimeLogFragment
  ) }
);


export const DeleteTimeLogDocument = gql`
    mutation deleteTimeLog($id: ID!) {
  deleteTimeLog(id: $id) {
    ...fullTimeLog
  }
}
    ${FullTimeLogFragmentDoc}`;
export type GqlDeleteTimeLogMutationFn = Apollo.MutationFunction<GqlDeleteTimeLogMutation, GqlDeleteTimeLogMutationVariables>;

/**
 * __useDeleteTimeLogMutation__
 *
 * To run a mutation, you first call `useDeleteTimeLogMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useDeleteTimeLogMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [deleteTimeLogMutation, { data, loading, error }] = useDeleteTimeLogMutation({
 *   variables: {
 *      id: // value for 'id'
 *   },
 * });
 */
export function useDeleteTimeLogMutation(baseOptions?: Apollo.MutationHookOptions<GqlDeleteTimeLogMutation, GqlDeleteTimeLogMutationVariables>) {
        return Apollo.useMutation<GqlDeleteTimeLogMutation, GqlDeleteTimeLogMutationVariables>(DeleteTimeLogDocument, baseOptions);
      }
export type DeleteTimeLogMutationHookResult = ReturnType<typeof useDeleteTimeLogMutation>;
export type DeleteTimeLogMutationResult = Apollo.MutationResult<GqlDeleteTimeLogMutation>;
export type DeleteTimeLogMutationOptions = Apollo.BaseMutationOptions<GqlDeleteTimeLogMutation, GqlDeleteTimeLogMutationVariables>;