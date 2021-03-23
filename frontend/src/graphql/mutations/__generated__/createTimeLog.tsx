/* eslint-disable */
import * as Types from '../../types';

import { GqlFullTimeLogFragment } from '../../fragments/__generated__/fullTimeLog';
import { gql } from '@apollo/client';
import { FullTimeLogFragmentDoc } from '../../fragments/__generated__/fullTimeLog';
import * as Apollo from '@apollo/client';
export type GqlCreateTimeLogMutationVariables = Types.Exact<{
  input: Types.GqlTimeLogInput;
}>;


export type GqlCreateTimeLogMutation = (
  { __typename?: 'Mutation' }
  & { createTimeLog: (
    { __typename?: 'TimeLog' }
    & GqlFullTimeLogFragment
  ) }
);


export const CreateTimeLogDocument = gql`
    mutation createTimeLog($input: TimeLogInput!) {
  createTimeLog(input: $input) {
    ...fullTimeLog
  }
}
    ${FullTimeLogFragmentDoc}`;
export type GqlCreateTimeLogMutationFn = Apollo.MutationFunction<GqlCreateTimeLogMutation, GqlCreateTimeLogMutationVariables>;

/**
 * __useCreateTimeLogMutation__
 *
 * To run a mutation, you first call `useCreateTimeLogMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useCreateTimeLogMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [createTimeLogMutation, { data, loading, error }] = useCreateTimeLogMutation({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useCreateTimeLogMutation(baseOptions?: Apollo.MutationHookOptions<GqlCreateTimeLogMutation, GqlCreateTimeLogMutationVariables>) {
        return Apollo.useMutation<GqlCreateTimeLogMutation, GqlCreateTimeLogMutationVariables>(CreateTimeLogDocument, baseOptions);
      }
export type CreateTimeLogMutationHookResult = ReturnType<typeof useCreateTimeLogMutation>;
export type CreateTimeLogMutationResult = Apollo.MutationResult<GqlCreateTimeLogMutation>;
export type CreateTimeLogMutationOptions = Apollo.BaseMutationOptions<GqlCreateTimeLogMutation, GqlCreateTimeLogMutationVariables>;