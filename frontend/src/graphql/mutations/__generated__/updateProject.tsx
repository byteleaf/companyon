/* eslint-disable */
import * as Types from '../../types';

import { GqlFullProjectFragment } from '../../fragments/__generated__/fullProject';
import { gql } from '@apollo/client';
import { FullProjectFragmentDoc } from '../../fragments/__generated__/fullProject';
import * as Apollo from '@apollo/client';
export type GqlUpdateProjectMutationVariables = Types.Exact<{
  input: Types.GqlProjectInput;
  id: Types.Scalars['ID'];
}>;


export type GqlUpdateProjectMutation = (
  { __typename?: 'Mutation' }
  & { updateProject: (
    { __typename?: 'Project' }
    & GqlFullProjectFragment
  ) }
);


export const UpdateProjectDocument = gql`
    mutation updateProject($input: ProjectInput!, $id: ID!) {
  updateProject(input: $input, id: $id) {
    ...fullProject
  }
}
    ${FullProjectFragmentDoc}`;
export type GqlUpdateProjectMutationFn = Apollo.MutationFunction<GqlUpdateProjectMutation, GqlUpdateProjectMutationVariables>;

/**
 * __useUpdateProjectMutation__
 *
 * To run a mutation, you first call `useUpdateProjectMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useUpdateProjectMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [updateProjectMutation, { data, loading, error }] = useUpdateProjectMutation({
 *   variables: {
 *      input: // value for 'input'
 *      id: // value for 'id'
 *   },
 * });
 */
export function useUpdateProjectMutation(baseOptions?: Apollo.MutationHookOptions<GqlUpdateProjectMutation, GqlUpdateProjectMutationVariables>) {
        return Apollo.useMutation<GqlUpdateProjectMutation, GqlUpdateProjectMutationVariables>(UpdateProjectDocument, baseOptions);
      }
export type UpdateProjectMutationHookResult = ReturnType<typeof useUpdateProjectMutation>;
export type UpdateProjectMutationResult = Apollo.MutationResult<GqlUpdateProjectMutation>;
export type UpdateProjectMutationOptions = Apollo.BaseMutationOptions<GqlUpdateProjectMutation, GqlUpdateProjectMutationVariables>;