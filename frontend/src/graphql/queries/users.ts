import { gql } from '@apollo/client';
import fullUser from '../fragments/fullUser';

export default gql`
  query users {
    users {
      ...fullUser
    }
  }

  ${fullUser}
`;
