import { gql } from '@apollo/client';
import fullProject from '../fragments/fullProject';

export default gql`
  mutation createProject($input: ProjectInput!) {
    createProject(input: $input) {
      ...fullProject
    }
  }

  ${fullProject}
`;
