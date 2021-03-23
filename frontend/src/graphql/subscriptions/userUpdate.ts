import { gql } from '@apollo/client';
import fullUser from '../fragments/fullUser';

export default gql`
  subscription userUpdate {
    userUpdate {
      type
      entity {
        ...fullUser
      }
    }
  }
  ${fullUser}
`;
