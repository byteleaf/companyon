import { MockedProvider, MockedResponse } from '@apollo/client/testing';
import React from 'react';
import { CurrentUserDocument, GqlCurrentUserQuery } from '../../../graphql/queries/__generated__/currentUser';
import { GqlProjectsQuery, ProjectsDocument } from '../../../graphql/queries/__generated__/projects';
import { GqlProjectState } from '../../../graphql/types';
import TimeLoggingBar from './TimeLoggingBar';

export default {
  component: TimeLoggingBar,
  title: 'Organisms/Time Logging/Bar',
};

export const Default = () => {
  const mocks: MockedResponse<GqlCurrentUserQuery | GqlProjectsQuery>[] = [
    {
      request: { query: CurrentUserDocument },
      result: {
        data: {
          currentUser: {
            id: 'id',
            firstName: 'First',
            lastName: 'Last',
            email: 'email',
            admin: true,
          },
        },
      },
    },
    {
      request: { query: ProjectsDocument },
      result: {
        data: {
          projects: [{ id: 'id', name: 'projectA', state: GqlProjectState.InProgress }],
        },
      },
    },
  ];

  return (
    <MockedProvider mocks={mocks}>
      <TimeLoggingBar callback={jest.fn} />
    </MockedProvider>
  );
};
