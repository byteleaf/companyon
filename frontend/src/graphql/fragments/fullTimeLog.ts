import { gql } from '@apollo/client';
import fullProject from './fullProject';
import fullUser from './fullUser';

export default gql`
  fragment fullTimeLog on TimeLog {
    id
    user {
      ...fullUser
    }
    project {
      ...fullProject
    }
    description
    start
    durationInMinutes
    breakInMinutes
  }
  ${fullProject}
  ${fullUser}
`;
