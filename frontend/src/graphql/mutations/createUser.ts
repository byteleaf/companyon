import { gql } from '@apollo/client';
import fullUser from '../fragments/fullUser';

export default gql`
  mutation createUser($input: UserInput!) {
    createUser(input: $input) {
      ...fullUser
    }
  }

  ${fullUser}
`;
