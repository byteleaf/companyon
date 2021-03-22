import { gql } from '@apollo/client';

export default gql`
  mutation deleteCompany($id: ID!) {
    deleteCompany(id: $id) {
      id
      name
    }
  }
`;
