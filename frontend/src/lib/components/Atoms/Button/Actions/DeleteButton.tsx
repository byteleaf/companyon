import React from 'react';
import { useDeleteCompanyMutation } from '../../../../../graphql/mutations/__generated__/deleteCompany';
import { useDeleteProjectMutation } from '../../../../../graphql/mutations/__generated__/deleteProject';
import { useDeleteUserMutation } from '../../../../../graphql/mutations/__generated__/deleteUser';
import { TableRow, TableRowAction, TableRowActions } from '../../../Organisms/DataTable/types';
import TextButton from '../TextButton';

export type DeleteButtonProps = {
  action: TableRowAction;
  row: TableRow;
  withMargin: boolean;
  type: TableRowActions;
};

export default ({ action: { label }, row, withMargin, type }: DeleteButtonProps) => {
  const [deleteCompany, { loading: companyLoading }] = useDeleteCompanyMutation({
    onError: (err) => {
      console.error(err);
    },
  });

  const [deleteProject, { loading: projectsLoading }] = useDeleteProjectMutation({
    onError: (err) => {
      console.error(err);
    },
  });

  const [deleteUser, { loading: userLoading }] = useDeleteUserMutation({
    onError: (err) => {
      console.error(err);
    },
  });

  const handleClick = () => {
    const params = { variables: { id: row.id } };
    switch (type) {
      case 'DeleteCompany':
        deleteCompany(params);
        break;
      case 'DeleteProject':
        deleteProject(params);
        break;
      case 'DeleteUser':
        deleteUser(params);
        break;
      default:
        break;
    }
  };

  return (
    <TextButton
      disabled={companyLoading || projectsLoading || userLoading}
      onClick={handleClick}
      key={label}
      className={`${withMargin && 'mr-3'}`}
    >
      {label}
    </TextButton>
  );
};
