/* eslint-disable */
import * as Types from '../../types';

import { gql } from '@apollo/client';
export type GqlFullUserFragment = (
  { __typename?: 'User' }
  & Pick<Types.GqlUser, 'id' | 'firstName' | 'lastName' | 'email' | 'admin'>
  & { avatar?: Types.Maybe<(
    { __typename?: 'FileMeta' }
    & Pick<Types.GqlFileMeta, 'url'>
  )> }
);

export const FullUserFragmentDoc = gql`
    fragment fullUser on User {
  id
  firstName
  lastName
  email
  admin
  avatar {
    url
  }
}
    `;