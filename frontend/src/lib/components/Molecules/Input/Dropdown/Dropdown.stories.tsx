import React, { useState } from 'react';
import { action } from '@storybook/addon-actions';
import { sortBy } from 'lodash';
import Dropdown from './Dropdown';
import { KeyValuePair } from '../../../../../types';

export default {
  component: Dropdown,
  title: 'Molecules/Input/Dropdown',
};

const languages: KeyValuePair<string>[] = sortBy(
  [
    {
      key: 'c',
      value: 'C',
    },
    {
      key: 'elm',
      value: 'Elm',
    },
    {
      key: 'cplusplus',
      value: 'C++',
    },
    {
      key: 'js',
      value: 'JS',
    },
    {
      key: 'python',
      value: 'Python',
    },
    {
      key: 'go',
      value: 'Go',
    },
    {
      key: 'kotlin',
      value: 'Kotlin',
    },
    {
      key: 'java',
      value: 'Java',
    },
    {
      key: 'ruby',
      value: 'Ruby',
    },
    {
      key: 'php',
      value: 'PHP',
    },
    {
      key: 'scala',
      value: 'Scala',
    },
    {
      key: 'csharp',
      value: 'C#',
    },
    {
      key: 'r',
      value: 'R',
    },
    {
      key: 'vba',
      value: 'VBA',
    },
    {
      key: 'perl',
      value: 'Perl',
    },
    {
      key: 'cobol',
      value: 'COBOL',
    },
  ],
  [(item) => item.value.toLowerCase()],
);

export const Default = () => {
  const [value, setValue] = useState('');

  const onChange = (key: string) => {
    action('onChange')(key);
    setValue(key);
  };

  return (
    <Dropdown id="storybook-default" label="Pick your language" items={languages} value={value} onChange={onChange} />
  );
};

export const Prefilled = () => {
  const [value, setValue] = useState('c');

  const onChange = (key: string) => {
    action('onChange')(key);
    setValue(key);
  };

  return (
    <Dropdown id="storybook-prefilled" label="Pick your language" items={languages} value={value} onChange={onChange} />
  );
};

export const Required = () => {
  const [value, setValue] = useState('');

  const onChange = (key: string) => {
    action('onChange')(key);
    setValue(key);
  };

  return (
    <Dropdown
      id="storybook-required"
      label="Pick your language"
      items={languages}
      value={value}
      required
      onChange={onChange}
    />
  );
};

export const Error = () => {
  const [value, setValue] = useState('cobol');

  const onChange = (key: string) => {
    action('onChange')(key);
    setValue(key);
  };

  return (
    <Dropdown
      id="storybook-error"
      label="Pick your language"
      error="This is an error!"
      items={languages}
      value={value}
      onChange={onChange}
    />
  );
};
