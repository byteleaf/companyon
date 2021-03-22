import { gql } from '@apollo/client';

export default gql`
  mutation createCompany($input: CompanyInput!) {
    createCompany(input: $input) {
      id
      name
    }
  }
`;
