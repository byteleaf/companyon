import { gql } from '@apollo/client';
import fullCompany from './fullCompany';

export default gql`
  fragment fullProject on Project {
    id
    name
    state
    company {
      ...fullCompany
    }
  }
  ${fullCompany}
`;
