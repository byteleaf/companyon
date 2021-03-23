import { gql } from '@apollo/client';
import fullProject from '../fragments/fullProject';

export default gql`
  query project($id: ID!) {
    project(id: $id) {
      ...fullProject
    }
  }
  ${fullProject}
`;
