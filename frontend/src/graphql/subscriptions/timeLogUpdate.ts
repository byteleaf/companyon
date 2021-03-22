import { gql } from '@apollo/client';
import fullTimeLog from '../fragments/fullTimeLog';

export default gql`
  subscription timeLogUpdate {
    timeLogUpdate {
      type
      entity {
        ...fullTimeLog
      }
    }
  }
  ${fullTimeLog}
`;
