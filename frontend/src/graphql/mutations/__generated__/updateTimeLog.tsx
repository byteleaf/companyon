/* eslint-disable */
import * as Types from '../../types';

import { GqlFullTimeLogFragment } from '../../fragments/__generated__/fullTimeLog';
import { gql } from '@apollo/client';
import { FullTimeLogFragmentDoc } from '../../fragments/__generated__/fullTimeLog';
import * as Apollo from '@apollo/client';
export type GqlUpdateTimeLogMutationVariables = Types.Exact<{
  id: Types.Scalars['ID'];
  input: Types.GqlTimeLogInput;
}>;


export type GqlUpdateTimeLogMutation = (
  { __typename?: 'Mutation' }
  & { updateTimeLog: (
    { __typename?: 'TimeLog' }
    & GqlFullTimeLogFragment
  ) }
);


export const UpdateTimeLogDocument = gql`
    mutation updateTimeLog($id: ID!, $input: TimeLogInput!) {
  updateTimeLog(id: $id, input: $input) {
    ...fullTimeLog
  }
}
    ${FullTimeLogFragmentDoc}`;
export type GqlUpdateTimeLogMutationFn = Apollo.MutationFunction<GqlUpdateTimeLogMutation, GqlUpdateTimeLogMutationVariables>;

/**
 * __useUpdateTimeLogMutation__
 *
 * To run a mutation, you first call `useUpdateTimeLogMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useUpdateTimeLogMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [updateTimeLogMutation, { data, loading, error }] = useUpdateTimeLogMutation({
 *   variables: {
 *      id: // value for 'id'
 *      input: // value for 'input'
 *   },
 * });
 */
export function useUpdateTimeLogMutation(baseOptions?: Apollo.MutationHookOptions<GqlUpdateTimeLogMutation, GqlUpdateTimeLogMutationVariables>) {
        return Apollo.useMutation<GqlUpdateTimeLogMutation, GqlUpdateTimeLogMutationVariables>(UpdateTimeLogDocument, baseOptions);
      }
export type UpdateTimeLogMutationHookResult = ReturnType<typeof useUpdateTimeLogMutation>;
export type UpdateTimeLogMutationResult = Apollo.MutationResult<GqlUpdateTimeLogMutation>;
export type UpdateTimeLogMutationOptions = Apollo.BaseMutationOptions<GqlUpdateTimeLogMutation, GqlUpdateTimeLogMutationVariables>;