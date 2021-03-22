import { MockedResponse } from '@apollo/client/testing';
import {
  GqlCreateCompanyMutation,
  CreateCompanyDocument,
} from '../../../../graphql/mutations/__generated__/createCompany';

export const companies = [
  { id: '1', name: 'byteleaf GmbH' },
  { id: '2', name: 'byteleaf Commerce' },
];

export const mocks: MockedResponse<GqlCreateCompanyMutation>[] = [
  {
    request: { query: CreateCompanyDocument, variables: { input: { name: companies[0].name } } },
    result: {
      data: {
        createCompany: companies[0],
      },
    },
  },
  {
    request: { query: CreateCompanyDocument, variables: { input: { name: companies[1].name } } },
    error: new Error('common.errors.alreadyExists'),
  },
];
