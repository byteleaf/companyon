import { gql } from '@apollo/client';
import fullTimeLog from '../fragments/fullTimeLog';

export default gql`
  query timeLogs($userId: String, $projectId: String, $from: OffsetDateTime, $to: OffsetDateTime) {
    timeLogs(userId: $userId, projectId: $projectId, from: $from, to: $to) {
      ...fullTimeLog
    }
  }
  ${fullTimeLog}
`;
