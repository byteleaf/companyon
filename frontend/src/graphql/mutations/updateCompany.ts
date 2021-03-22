import { gql } from '@apollo/client';

export default gql`
  mutation updateCompany($input: CompanyInput!, $id: ID!) {
    updateCompany(input: $input, id: $id) {
      id
      name
    }
  }
`;
