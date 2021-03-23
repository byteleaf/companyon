import { gql } from '@apollo/client';
import fullProject from '../fragments/fullProject';

export default gql`
  mutation updateProject($input: ProjectInput!, $id: ID!) {
    updateProject(input: $input, id: $id) {
      ...fullProject
    }
  }

  ${fullProject}
`;
