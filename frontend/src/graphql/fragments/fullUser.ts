import { gql } from '@apollo/client';

export default gql`
  fragment fullUser on User {
    id
    firstName
    lastName
    email
    admin
    avatar {
      url
    }
  }
`;
