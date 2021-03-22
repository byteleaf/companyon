import { gql } from '@apollo/client';
import fullUser from '../fragments/fullUser';

export default gql`
  query user($id: ID!) {
    user(id: $id) {
      ...fullUser
    }
  }

  ${fullUser}
`;
