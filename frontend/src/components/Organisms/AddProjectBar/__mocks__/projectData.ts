import { MockedResponse } from '@apollo/client/testing';
import { GqlCompaniesQuery, CompaniesDocument } from '../../../../graphql/queries/__generated__/companies';
import {
  GqlCreateProjectMutation,
  CreateProjectDocument,
} from '../../../../graphql/mutations/__generated__/createProject';
import { GqlProjectState } from '../../../../graphql/types';

export const project = { id: '1', name: 'New Project', state: GqlProjectState.InProgress };

export const companies = [
  { id: '1', name: 'byteleaf GmbH' },
  { id: '2', name: 'byteleaf Commerce' },
];

export const mocks: MockedResponse<GqlCompaniesQuery | GqlCreateProjectMutation>[] = [
  {
    request: { query: CompaniesDocument },
    result: {
      data: {
        companies: [
          { __typename: 'Company', ...companies[0] },
          { __typename: 'Company', ...companies[1] },
        ],
      },
    },
  },
  {
    request: { query: CreateProjectDocument, variables: { input: { name: project.name, company: companies[0].id } } },
    result: {
      data: {
        createProject: {
          ...project,
          company: companies[0],
        },
      },
    },
  },
  {
    request: { query: CreateProjectDocument, variables: { input: { name: project.name, company: companies[1].id } } },
    error: new Error('common.errors.alreadyExists'),
  },
];
