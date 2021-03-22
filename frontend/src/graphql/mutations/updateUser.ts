import { gql } from '@apollo/client';
import fullUser from '../fragments/fullUser';

export default gql`
  mutation updateUser($input: UserInput!, $id: ID!) {
    updateUser(input: $input, id: $id) {
      ...fullUser
    }
  }

  ${fullUser}
`;
