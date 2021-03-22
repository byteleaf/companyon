import React from 'react';
import { MockedProvider, MockedResponse } from '@apollo/client/testing';
import { action } from '@storybook/addon-actions';
import { GraphQLError } from 'graphql';
import AddProjectBar from './AddProjectBar';
import { CompaniesDocument, GqlCompaniesQuery } from '../../../graphql/queries/__generated__/companies';
import {
  CreateProjectDocument,
  GqlCreateProjectMutation,
  GqlCreateProjectMutationVariables,
} from '../../../graphql/mutations/__generated__/createProject';
import { GqlProjectState } from '../../../graphql/types';

export default {
  component: AddProjectBar,
  title: 'Organisms/Project/AddProjectBar',
};

export const Default = () => {
  const mocks: MockedResponse<GqlCompaniesQuery | GqlCreateProjectMutation>[] = [
    {
      request: { query: CompaniesDocument },
      result: {
        data: {
          companies: [
            { __typename: 'Company', id: '1', name: 'Byteleaf GmbH' },
            { __typename: 'Company', id: '2', name: 'Jeff Inc' },
          ],
        },
      },
    },
    {
      request: {
        query: CreateProjectDocument,
        variables: { input: { company: '1', name: 'Project' } } as GqlCreateProjectMutationVariables,
      },
      result: {
        data: {
          createProject: {
            __typename: 'Project',
            id: 'proj1',
            name: 'Project',
            company: { id: '1', name: 'Byteleaf GmbH', __typename: 'Company' },
            state: GqlProjectState.InProgress,
          },
        },
      },
    },

    {
      request: {
        query: CreateProjectDocument,
        variables: { input: { company: '2', name: 'Project' } } as GqlCreateProjectMutationVariables,
      },
      result: {
        errors: [
          new GraphQLError(
            'Exception while fetching data (/createProject) : company not found',
            undefined,
            undefined,
            undefined,
            undefined,
            undefined,
            { i18n: 'common.errors.entityNotFound' },
          ),
        ],
      },
    },
  ];

  return (
    <MockedProvider mocks={mocks}>
      <AddProjectBar onError={action('onError')} />
    </MockedProvider>
  );
};
