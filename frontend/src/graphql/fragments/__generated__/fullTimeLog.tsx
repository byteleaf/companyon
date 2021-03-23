/* eslint-disable */
import * as Types from '../../types';

import { GqlFullUserFragment } from './fullUser';
import { GqlFullProjectFragment } from './fullProject';
import { gql } from '@apollo/client';
import { FullUserFragmentDoc } from './fullUser';
import { FullProjectFragmentDoc } from './fullProject';
export type GqlFullTimeLogFragment = (
  { __typename?: 'TimeLog' }
  & Pick<Types.GqlTimeLog, 'id' | 'description' | 'start' | 'durationInMinutes' | 'breakInMinutes'>
  & { user?: Types.Maybe<(
    { __typename?: 'User' }
    & GqlFullUserFragment
  )>, project?: Types.Maybe<(
    { __typename?: 'Project' }
    & GqlFullProjectFragment
  )> }
);

export const FullTimeLogFragmentDoc = gql`
    fragment fullTimeLog on TimeLog {
  id
  user {
    ...fullUser
  }
  project {
    ...fullProject
  }
  description
  start
  durationInMinutes
  breakInMinutes
}
    ${FullUserFragmentDoc}
${FullProjectFragmentDoc}`;