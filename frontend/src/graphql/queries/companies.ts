import { gql } from '@apollo/client';
import fullCompany from '../fragments/fullCompany';

export default gql`
  query companies {
    companies {
      ...fullCompany
    }
  }
  ${fullCompany}
`;
