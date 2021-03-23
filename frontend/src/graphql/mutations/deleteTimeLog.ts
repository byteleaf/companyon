import { gql } from '@apollo/client';
import fullTimeLog from '../fragments/fullTimeLog';

export default gql`
  mutation deleteTimeLog($id: ID!) {
    deleteTimeLog(id: $id) {
      ...fullTimeLog
    }
  }

  ${fullTimeLog}
`;
