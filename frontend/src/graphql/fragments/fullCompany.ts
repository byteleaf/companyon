import { gql } from '@apollo/client';

export default gql`
  fragment fullCompany on Company {
    id
    name
  }
`;
