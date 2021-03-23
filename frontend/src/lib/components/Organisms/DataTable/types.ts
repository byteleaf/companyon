import { KeyValuePair } from '../../../../types';

export enum TableRowActions {
  Link = 'Link',
  DeleteCompany = 'DeleteCompany',
  DeleteProject = 'DeleteProject',
  DeleteUser = 'DeleteUser',
}

export type TableRowAction = {
  type: keyof typeof TableRowActions;
  src: string;
  label: string;
};

export type TableCell = KeyValuePair<string | number>;

export type TableRow = {
  id: string;
  cells: TableCell[];
};

export type TableHeader = KeyValuePair<string | number>;

export type TableConfig = {
  rowActions: TableRowAction[];
  headers: TableHeader[];
};

export type TableData = {
  rows: TableRow[];
};
