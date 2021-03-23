import { gql } from '@apollo/client';
import fullProject from '../fragments/fullProject';

export default gql`
  query projects {
    projects {
      ...fullProject
    }
  }

  ${fullProject}
`;
