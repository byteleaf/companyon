import React from 'react';
import TimeLoggingEntry from './TimeLoggingEntry';

export default {
  component: TimeLoggingEntry,
  title: 'Organisms/Time Logging/Entry',
};

const sample = {
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
      value: 'Bytelea',
      hexColor: '#276749',
    },
  ],
};

export const Default = () => {
  return <TimeLoggingEntry {...sample} />;
};
