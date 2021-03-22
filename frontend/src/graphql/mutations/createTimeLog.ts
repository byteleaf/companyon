import { gql } from '@apollo/client';
import fullTimeLog from '../fragments/fullTimeLog';

export default gql`
  mutation createTimeLog($input: TimeLogInput!) {
    createTimeLog(input: $input) {
      ...fullTimeLog
    }
  }

  ${fullTimeLog}
`;
