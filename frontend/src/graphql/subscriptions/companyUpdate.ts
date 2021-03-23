import { gql } from '@apollo/client';
import fullCompany from '../fragments/fullCompany';

export default gql`
  subscription companyUpdate {
    companyUpdate {
      type
      entity {
        ...fullCompany
      }
    }
  }
  ${fullCompany}
`;
