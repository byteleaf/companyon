/* eslint-disable */
import * as Types from '../../types';

import { GqlFullUserFragment } from '../../fragments/__generated__/fullUser';
import { gql } from '@apollo/client';
import { FullUserFragmentDoc } from '../../fragments/__generated__/fullUser';
import * as Apollo from '@apollo/client';
export type GqlCreateUserMutationVariables = Types.Exact<{
  input: Types.GqlUserInput;
}>;


export type GqlCreateUserMutation = (
  { __typename?: 'Mutation' }
  & { createUser: (
    { __typename?: 'User' }
    & GqlFullUserFragment
  ) }
);


export const CreateUserDocument = gql`
    mutation createUser($input: UserInput!) {
  createUser(input: $input) {
    ...fullUser
  }
}
    ${FullUserFragmentDoc}`;
export type GqlCreateUserMutationFn = Apollo.MutationFunction<GqlCreateUserMutation, GqlCreateUserMutationVariables>;

/**
 * __useCreateUserMutation__
 *
 * To run a mutation, you first call `useCreateUserMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useCreateUserMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [createUserMutation, { data, loading, error }] = useCreateUserMutation({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useCreateUserMutation(baseOptions?: Apollo.MutationHookOptions<GqlCreateUserMutation, GqlCreateUserMutationVariables>) {
        return Apollo.useMutation<GqlCreateUserMutation, GqlCreateUserMutationVariables>(CreateUserDocument, baseOptions);
      }
export type CreateUserMutationHookResult = ReturnType<typeof useCreateUserMutation>;
export type CreateUserMutationResult = Apollo.MutationResult<GqlCreateUserMutation>;
export type CreateUserMutationOptions = Apollo.BaseMutationOptions<GqlCreateUserMutation, GqlCreateUserMutationVariables>;