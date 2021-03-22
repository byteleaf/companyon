import { gql } from '@apollo/client';
import fullUser from '../fragments/fullUser';

export default gql`
  query currentUser {
    currentUser {
      ...fullUser
    }
  }
  ${fullUser}
`;
