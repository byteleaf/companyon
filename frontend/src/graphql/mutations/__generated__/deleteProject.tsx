/* eslint-disable */
import * as Types from '../../types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
export type GqlDeleteProjectMutationVariables = Types.Exact<{
  id: Types.Scalars['ID'];
}>;


export type GqlDeleteProjectMutation = (
  { __typename?: 'Mutation' }
  & { deleteProject: (
    { __typename?: 'Project' }
    & Pick<Types.GqlProject, 'id' | 'name'>
  ) }
);


export const DeleteProjectDocument = gql`
    mutation deleteProject($id: ID!) {
  deleteProject(id: $id) {
    id
    name
  }
}
    `;
export type GqlDeleteProjectMutationFn = Apollo.MutationFunction<GqlDeleteProjectMutation, GqlDeleteProjectMutationVariables>;

/**
 * __useDeleteProjectMutation__
 *
 * To run a mutation, you first call `useDeleteProjectMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useDeleteProjectMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [deleteProjectMutation, { data, loading, error }] = useDeleteProjectMutation({
 *   variables: {
 *      id: // value for 'id'
 *   },
 * });
 */
export function useDeleteProjectMutation(baseOptions?: Apollo.MutationHookOptions<GqlDeleteProjectMutation, GqlDeleteProjectMutationVariables>) {
        return Apollo.useMutation<GqlDeleteProjectMutation, GqlDeleteProjectMutationVariables>(DeleteProjectDocument, baseOptions);
      }
export type DeleteProjectMutationHookResult = ReturnType<typeof useDeleteProjectMutation>;
export type DeleteProjectMutationResult = Apollo.MutationResult<GqlDeleteProjectMutation>;
export type DeleteProjectMutationOptions = Apollo.BaseMutationOptions<GqlDeleteProjectMutation, GqlDeleteProjectMutationVariables>;