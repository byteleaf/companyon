import React from 'react';
import TimeLoggingList from './TimeLoggingList';

export default {
  component: TimeLoggingList,
  title: 'Organisms/Time Logging/List',
};

const sample = [
  {
    id: '1',
    title: 'Developed time logging feature',
    time: '12:00 - 16:30',
    date: '2020-01-07',
    project: 'Project',
    company: 'Company',
    tags: [
      {
        id: '1',
        value: 'Companyon',
        hexColor: '#276749',
        outline: true,
      },
      {
        id: '2',
        value: 'Byteleaf',
        hexColor: '#276749',
      },
    ],
  },
  {
    id: '2',
    title: 'Developed something else',
    time: '12:00 - 16:30',
    date: '2020-01-07',
    project: 'Project',
    company: 'Company',
    tags: [
      {
        id: '1',
        value: 'Companyon',
        hexColor: '#276749',
        outline: true,
      },
      {
        id: '2',
        value: 'Byteleaf',
        hexColor: '#276749',
      },
    ],
  },
  {
    id: '3',
    title: 'blabla',
    time: '12:00 - 16:30',
    date: '2020-01-07',
    project: 'Project',
    company: 'Company',
    tags: [
      {
        id: '1',
        value: 'Companyon',
        hexColor: '#276749',
        outline: true,
      },
      {
        id: '2',
        value: 'Byteleaf',
        hexColor: '#276749',
      },
    ],
  },
];

export const Default = () => {
  return <TimeLoggingList entries={sample} />;
};
