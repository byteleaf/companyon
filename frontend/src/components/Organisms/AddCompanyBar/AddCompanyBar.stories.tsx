import React from 'react';
import { MockedProvider, MockedResponse } from '@apollo/client/testing';
import { action } from '@storybook/addon-actions';
import AddCompanyBar from './AddCompanyBar';
import {
  CreateCompanyDocument,
  GqlCreateCompanyMutation,
} from '../../../graphql/mutations/__generated__/createCompany';

export default {
  component: AddCompanyBar,
  title: 'Organisms/Company/AddCompanyBar',
};

export const Default = () => {
  const mocks: MockedResponse<GqlCreateCompanyMutation>[] = [
    {
      request: { query: CreateCompanyDocument, variables: { input: { name: 'Byteleaf GmbH' } } },
      error: new Error('common.errors.alreadyExists'),
    },
    {
      request: { query: CreateCompanyDocument, variables: { input: { name: 'New Company' } } },
      result: {
        data: {
          createCompany: {
            id: '1337',
            name: 'New Company',
          },
        },
      },
    },
  ];

  return (
    <MockedProvider mocks={mocks}>
      <AddCompanyBar onError={action('onError')} />
    </MockedProvider>
  );
};
