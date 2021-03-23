/* eslint-disable */
import * as Types from '../../types';

import { gql } from '@apollo/client';
export type GqlFullCompanyFragment = (
  { __typename?: 'Company' }
  & Pick<Types.GqlCompany, 'id' | 'name'>
);

export const FullCompanyFragmentDoc = gql`
    fragment fullCompany on Company {
  id
  name
}
    `;