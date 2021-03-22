import { gql } from '@apollo/client';
import fullTimeLog from '../fragments/fullTimeLog';

export default gql`
  mutation updateTimeLog($id: ID!, $input: TimeLogInput!) {
    updateTimeLog(id: $id, input: $input) {
      ...fullTimeLog
    }
  }

  ${fullTimeLog}
`;
