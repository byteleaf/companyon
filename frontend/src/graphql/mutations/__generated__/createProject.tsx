/* eslint-disable */
import * as Types from '../../types';

import { GqlFullProjectFragment } from '../../fragments/__generated__/fullProject';
import { gql } from '@apollo/client';
import { FullProjectFragmentDoc } from '../../fragments/__generated__/fullProject';
import * as Apollo from '@apollo/client';
export type GqlCreateProjectMutationVariables = Types.Exact<{
  input: Types.GqlProjectInput;
}>;


export type GqlCreateProjectMutation = (
  { __typename?: 'Mutation' }
  & { createProject: (
    { __typename?: 'Project' }
    & GqlFullProjectFragment
  ) }
);


export const CreateProjectDocument = gql`
    mutation createProject($input: ProjectInput!) {
  createProject(input: $input) {
    ...fullProject
  }
}
    ${FullProjectFragmentDoc}`;
export type GqlCreateProjectMutationFn = Apollo.MutationFunction<GqlCreateProjectMutation, GqlCreateProjectMutationVariables>;

/**
 * __useCreateProjectMutation__
 *
 * To run a mutation, you first call `useCreateProjectMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useCreateProjectMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [createProjectMutation, { data, loading, error }] = useCreateProjectMutation({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useCreateProjectMutation(baseOptions?: Apollo.MutationHookOptions<GqlCreateProjectMutation, GqlCreateProjectMutationVariables>) {
        return Apollo.useMutation<GqlCreateProjectMutation, GqlCreateProjectMutationVariables>(CreateProjectDocument, baseOptions);
      }
export type CreateProjectMutationHookResult = ReturnType<typeof useCreateProjectMutation>;
export type CreateProjectMutationResult = Apollo.MutationResult<GqlCreateProjectMutation>;
export type CreateProjectMutationOptions = Apollo.BaseMutationOptions<GqlCreateProjectMutation, GqlCreateProjectMutationVariables>;