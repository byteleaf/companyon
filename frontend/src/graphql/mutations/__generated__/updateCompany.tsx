/* eslint-disable */
import * as Types from '../../types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
export type GqlUpdateCompanyMutationVariables = Types.Exact<{
  input: Types.GqlCompanyInput;
  id: Types.Scalars['ID'];
}>;


export type GqlUpdateCompanyMutation = (
  { __typename?: 'Mutation' }
  & { updateCompany: (
    { __typename?: 'Company' }
    & Pick<Types.GqlCompany, 'id' | 'name'>
  ) }
);


export const UpdateCompanyDocument = gql`
    mutation updateCompany($input: CompanyInput!, $id: ID!) {
  updateCompany(input: $input, id: $id) {
    id
    name
  }
}
    `;
export type GqlUpdateCompanyMutationFn = Apollo.MutationFunction<GqlUpdateCompanyMutation, GqlUpdateCompanyMutationVariables>;

/**
 * __useUpdateCompanyMutation__
 *
 * To run a mutation, you first call `useUpdateCompanyMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useUpdateCompanyMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [updateCompanyMutation, { data, loading, error }] = useUpdateCompanyMutation({
 *   variables: {
 *      input: // value for 'input'
 *      id: // value for 'id'
 *   },
 * });
 */
export function useUpdateCompanyMutation(baseOptions?: Apollo.MutationHookOptions<GqlUpdateCompanyMutation, GqlUpdateCompanyMutationVariables>) {
        return Apollo.useMutation<GqlUpdateCompanyMutation, GqlUpdateCompanyMutationVariables>(UpdateCompanyDocument, baseOptions);
      }
export type UpdateCompanyMutationHookResult = ReturnType<typeof useUpdateCompanyMutation>;
export type UpdateCompanyMutationResult = Apollo.MutationResult<GqlUpdateCompanyMutation>;
export type UpdateCompanyMutationOptions = Apollo.BaseMutationOptions<GqlUpdateCompanyMutation, GqlUpdateCompanyMutationVariables>;