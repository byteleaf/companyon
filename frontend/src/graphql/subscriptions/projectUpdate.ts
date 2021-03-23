import { gql } from '@apollo/client';
import fullProject from '../fragments/fullProject';

export default gql`
  subscription projectUpdate {
    projectUpdate {
      type
      entity {
        ...fullProject
      }
    }
  }
  ${fullProject}
`;
