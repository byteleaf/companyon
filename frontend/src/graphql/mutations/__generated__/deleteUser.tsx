/* eslint-disable */
import * as Types from '../../types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
export type GqlDeleteUserMutationVariables = Types.Exact<{
  id: Types.Scalars['ID'];
}>;


export type GqlDeleteUserMutation = (
  { __typename?: 'Mutation' }
  & { deleteUser: (
    { __typename?: 'User' }
    & Pick<Types.GqlUser, 'id'>
  ) }
);


export const DeleteUserDocument = gql`
    mutation deleteUser($id: ID!) {
  deleteUser(id: $id) {
    id
  }
}
    `;
export type GqlDeleteUserMutationFn = Apollo.MutationFunction<GqlDeleteUserMutation, GqlDeleteUserMutationVariables>;

/**
 * __useDeleteUserMutation__
 *
 * To run a mutation, you first call `useDeleteUserMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useDeleteUserMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [deleteUserMutation, { data, loading, error }] = useDeleteUserMutation({
 *   variables: {
 *      id: // value for 'id'
 *   },
 * });
 */
export function useDeleteUserMutation(baseOptions?: Apollo.MutationHookOptions<GqlDeleteUserMutation, GqlDeleteUserMutationVariables>) {
        return Apollo.useMutation<GqlDeleteUserMutation, GqlDeleteUserMutationVariables>(DeleteUserDocument, baseOptions);
      }
export type DeleteUserMutationHookResult = ReturnType<typeof useDeleteUserMutation>;
export type DeleteUserMutationResult = Apollo.MutationResult<GqlDeleteUserMutation>;
export type DeleteUserMutationOptions = Apollo.BaseMutationOptions<GqlDeleteUserMutation, GqlDeleteUserMutationVariables>;