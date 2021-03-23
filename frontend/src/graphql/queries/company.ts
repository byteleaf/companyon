import { gql } from '@apollo/client';
import fullCompany from '../fragments/fullCompany';

export default gql`
  query company($id: ID!) {
    company(id: $id) {
      ...fullCompany
    }
  }
  ${fullCompany}
`;
