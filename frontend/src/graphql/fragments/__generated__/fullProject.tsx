/* eslint-disable */
import * as Types from '../../types';

import { GqlFullCompanyFragment } from './fullCompany';
import { gql } from '@apollo/client';
import { FullCompanyFragmentDoc } from './fullCompany';
export type GqlFullProjectFragment = (
  { __typename?: 'Project' }
  & Pick<Types.GqlProject, 'id' | 'name' | 'state'>
  & { company?: Types.Maybe<(
    { __typename?: 'Company' }
    & GqlFullCompanyFragment
  )> }
);

export const FullProjectFragmentDoc = gql`
    fragment fullProject on Project {
  id
  name
  state
  company {
    ...fullCompany
  }
}
    ${FullCompanyFragmentDoc}`;