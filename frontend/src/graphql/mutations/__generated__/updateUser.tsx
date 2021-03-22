/* eslint-disable */
import * as Types from '../../types';

import { GqlFullUserFragment } from '../../fragments/__generated__/fullUser';
import { gql } from '@apollo/client';
import { FullUserFragmentDoc } from '../../fragments/__generated__/fullUser';
import * as Apollo from '@apollo/client';
export type GqlUpdateUserMutationVariables = Types.Exact<{
  input: Types.GqlUserInput;
  id: Types.Scalars['ID'];
}>;


export type GqlUpdateUserMutation = (
  { __typename?: 'Mutation' }
  & { updateUser: (
    { __typename?: 'User' }
    & GqlFullUserFragment
  ) }
);


export const UpdateUserDocument = gql`
    mutation updateUser($input: UserInput!, $id: ID!) {
  updateUser(input: $input, id: $id) {
    ...fullUser
  }
}
    ${FullUserFragmentDoc}`;
export type GqlUpdateUserMutationFn = Apollo.MutationFunction<GqlUpdateUserMutation, GqlUpdateUserMutationVariables>;

/**
 * __useUpdateUserMutation__
 *
 * To run a mutation, you first call `useUpdateUserMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useUpdateUserMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [updateUserMutation, { data, loading, error }] = useUpdateUserMutation({
 *   variables: {
 *      input: // value for 'input'
 *      id: // value for 'id'
 *   },
 * });
 */
export function useUpdateUserMutation(baseOptions?: Apollo.MutationHookOptions<GqlUpdateUserMutation, GqlUpdateUserMutationVariables>) {
        return Apollo.useMutation<GqlUpdateUserMutation, GqlUpdateUserMutationVariables>(UpdateUserDocument, baseOptions);
      }
export type UpdateUserMutationHookResult = ReturnType<typeof useUpdateUserMutation>;
export type UpdateUserMutationResult = Apollo.MutationResult<GqlUpdateUserMutation>;
export type UpdateUserMutationOptions = Apollo.BaseMutationOptions<GqlUpdateUserMutation, GqlUpdateUserMutationVariables>;