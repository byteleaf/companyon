import React from 'react';
import MockedRouter from '../../../../helpers/test/MockedRouter';
import DataTable from './DataTable';
import { TableConfig, TableData } from './types';

export default {
  component: DataTable,
  title: 'Organisms/Data Table',
};

const config: TableConfig = {
  rowActions: [
    {
      type: 'Link',
      src: 'companies',
      label: 'View',
    },
    {
      type: 'Link',
      src: 'companies',
      label: 'Delete',
    },
  ],
  headers: [
    { key: 'name', value: 'Project Name' },
    { key: 'company', value: 'Company' },
  ],
};

const data: TableData = {
  rows: [
    {
      id: '1',
      cells: [
        {
          key: 'name',
          value: 'Internal',
        },
        {
          key: 'company',
          value: 'byteleaf',
        },
      ],
    },
    {
      id: '2',
      cells: [
        {
          key: 'name',
          value: 'Customer Aquisition',
        },
        {
          key: 'company',
          value: 'byteleaf',
        },
      ],
    },
    {
      id: '3',
      cells: [
        {
          key: 'name',
          value: 'Accounting',
        },
        {
          key: 'company',
          value: 'byteleaf',
        },
      ],
    },
    {
      id: '4',
      cells: [
        {
          key: 'name',
          value: 'Development',
        },
        {
          key: 'company',
          value: 'Test AG',
        },
      ],
    },
    {
      id: '5',
      cells: [
        {
          key: 'name',
          value: 'Development',
        },
        {
          key: 'company',
          value: 'Test GmbH',
        },
      ],
    },
    {
      id: '6',
      cells: [
        {
          key: 'name',
          value: 'Testing',
        },
        {
          key: 'company',
          value: 'Test GmbH',
        },
      ],
    },
  ],
};

const emptyRowData: TableData = {
  rows: [],
};

export const Default = () => (
  <MockedRouter>
    <DataTable tableConfig={config} tableData={data} />
  </MockedRouter>
);

export const EmptyTable = () => (
  <MockedRouter>
    <DataTable tableConfig={config} tableData={emptyRowData} />
  </MockedRouter>
);
