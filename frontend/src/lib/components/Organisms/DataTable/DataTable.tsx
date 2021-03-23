import React, { Fragment } from 'react';
import { useTranslation } from 'react-i18next';
import DeleteButton from '../../Atoms/Button/Actions/DeleteButton';
import Link from '../../Atoms/Link/Link';
import { TableConfig, TableData, TableHeader, TableRow, TableRowAction, TableRowActions } from './types';

interface TableHeaderCellProps {
  last?: boolean;
}

const TableHeaderCell: React.FC<TableHeaderCellProps> = ({ children, last }) => (
  <th
    className={`px-6 py-3 border-b border-gray-200 bg-gray-50 text-xs leading-4 font-medium text-gray-500 uppercase tracking-wider ${
      last ? 'text-right' : 'text-left'
    }`.trim()}
  >
    {children}
  </th>
);

interface TableHeaderProps {
  headers: TableHeader[];
}

const TableHead: React.FC<TableHeaderProps> = ({ headers }) => {
  const { t } = useTranslation();

  return (
    <thead>
      <tr>
        {headers.map(({ key, value }) => (
          <TableHeaderCell key={key}>{value}</TableHeaderCell>
        ))}
        <TableHeaderCell last>{t('components.organisms.dataTable.header.actions')}</TableHeaderCell>
      </tr>
    </thead>
  );
};

interface TableBodyEmptyRowProps {
  cols: number;
}

const TableBodyEmptyRow: React.FC<TableBodyEmptyRowProps> = ({ cols }) => (
  <tr className="bg-white">
    <td colSpan={cols} className="px-6 py-4 whitespace-no-wrap text-sm text-center leading-5">
      Nothing to display...
    </td>
  </tr>
);

interface TableBodyRow {
  headers: TableHeader[];
  row: TableRow;
  rowActions: TableRowAction[];
  odd: boolean;
}

const TableBodyRow: React.FC<TableBodyRow> = ({ row, odd, headers, rowActions }) => (
  <tr className={odd ? 'bg-gray-50' : 'bg-white'}>
    {headers.map((headerRow, i) => {
      let className = 'text-gray-500';
      if (i === 0) {
        className = 'font-medium text-gray-900';
      }

      return (
        <td key={headerRow.key} className={`px-6 py-4 whitespace-no-wrap text-sm leading-5 ${className}`}>
          {row.cells.find((cell) => cell.key === headerRow.key)?.value}
        </td>
      );
    })}
    <td className="px-6 py-4 whitespace-no-wrap text-right text-sm leading-5 font-medium">
      {rowActions.map(({ type, src, label }, i) => {
        switch (type) {
          case 'DeleteCompany':
          case 'DeleteProject':
          case 'DeleteUser':
            return (
              <Fragment key={label}>
                <DeleteButton
                  action={{ src, label, type }}
                  row={row}
                  withMargin={i !== rowActions.length - 1}
                  type={type as TableRowActions}
                />
              </Fragment>
            );
          default:
            return (
              <Fragment key={label}>
                <Link to={`/${src}/${row.id}`} className={`${i !== rowActions.length - 1 && 'mr-3'}`}>
                  {label}
                </Link>
              </Fragment>
            );
        }
      })}
    </td>
  </tr>
);

interface TableBodyProps {
  tableConfig: TableConfig;
  tableData: TableData;
}

const TableBody: React.FC<TableBodyProps> = ({ tableConfig: { headers, rowActions }, tableData: { rows } }) => (
  <tbody>
    {rows.length !== 0 ? (
      rows.map((row, i) => (
        <Fragment key={row.id}>
          <TableBodyRow row={row} odd={i % 2 === 1} headers={headers} rowActions={rowActions} />
        </Fragment>
      ))
    ) : (
      <TableBodyEmptyRow cols={headers.length + 1} />
    )}
  </tbody>
);

interface DataTableProps {
  tableConfig: TableConfig;
  tableData: TableData;
  className?: string;
}

const DataTable: React.FC<DataTableProps> = ({ tableConfig, tableData, className = '' }) => {
  return (
    <div className={`flex flex-col ${className}`}>
      <div className="-my-2 py-2 overflow-x-auto sm:-mx-6 sm:px-6 lg:-mx-8 lg:px-8">
        <div className="align-middle inline-block min-w-full shadow overflow-hidden sm:rounded-lg border-b border-gray-200">
          <table className="min-w-full">
            <TableHead headers={tableConfig.headers} />
            <TableBody tableConfig={tableConfig} tableData={tableData} />
          </table>
        </div>
      </div>
    </div>
  );
};

export default DataTable;
