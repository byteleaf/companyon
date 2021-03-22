/* eslint-disable */
import * as Types from '../../types';

import { gql } from '@apollo/client';
import * as Apollo from '@apollo/client';
export type GqlCreateCompanyMutationVariables = Types.Exact<{
  input: Types.GqlCompanyInput;
}>;


export type GqlCreateCompanyMutation = (
  { __typename?: 'Mutation' }
  & { createCompany: (
    { __typename?: 'Company' }
    & Pick<Types.GqlCompany, 'id' | 'name'>
  ) }
);


export const CreateCompanyDocument = gql`
    mutation createCompany($input: CompanyInput!) {
  createCompany(input: $input) {
    id
    name
  }
}
    `;
export type GqlCreateCompanyMutationFn = Apollo.MutationFunction<GqlCreateCompanyMutation, GqlCreateCompanyMutationVariables>;

/**
 * __useCreateCompanyMutation__
 *
 * To run a mutation, you first call `useCreateCompanyMutation` within a React component and pass it any options that fit your needs.
 * When your component renders, `useCreateCompanyMutation` returns a tuple that includes:
 * - A mutate function that you can call at any time to execute the mutation
 * - An object with fields that represent the current status of the mutation's execution
 *
 * @param baseOptions options that will be passed into the mutation, supported options are listed on: https://www.apollographql.com/docs/react/api/react-hooks/#options-2;
 *
 * @example
 * const [createCompanyMutation, { data, loading, error }] = useCreateCompanyMutation({
 *   variables: {
 *      input: // value for 'input'
 *   },
 * });
 */
export function useCreateCompanyMutation(baseOptions?: Apollo.MutationHookOptions<GqlCreateCompanyMutation, GqlCreateCompanyMutationVariables>) {
        return Apollo.useMutation<GqlCreateCompanyMutation, GqlCreateCompanyMutationVariables>(CreateCompanyDocument, baseOptions);
      }
export type CreateCompanyMutationHookResult = ReturnType<typeof useCreateCompanyMutation>;
export type CreateCompanyMutationResult = Apollo.MutationResult<GqlCreateCompanyMutation>;
export type CreateCompanyMutationOptions = Apollo.BaseMutationOptions<GqlCreateCompanyMutation, GqlCreateCompanyMutationVariables>;