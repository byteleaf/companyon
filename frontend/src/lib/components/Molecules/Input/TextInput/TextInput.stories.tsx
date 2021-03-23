import React, { useState } from 'react';
import { action } from '@storybook/addon-actions';
import TextInput from './TextInput';

export default {
  component: TextInput,
  title: 'Molecules/Input/Text Input',
};

export const Classic = () => {
  const [value, setValue] = useState<string>('');

  const onChange = (newVal: string) => {
    action('change')(newVal);
    setValue(newVal);
  };

  return <TextInput id="storybook-classic" label="Name" value={value} placeholder="Jeff" onChange={onChange} />;
};

export const WithoutLabel = () => {
  const [value, setValue] = useState<string>('');

  const onChange = (newVal: string) => {
    action('change')(newVal);
    setValue(newVal);
  };

  return <TextInput id="storybook-classic" value={value} placeholder="Jeff" onChange={onChange} />;
};

export const Number = () => {
  const [value, setValue] = useState<string>('20');

  const onChange = (newVal: string) => {
    action('change')(newVal);
    setValue(newVal);
  };

  return <TextInput id="storybook-classic" label="Name" value={value} type="number" onChange={onChange} />;
};

export const Required = () => {
  const [value, setValue] = useState<string>('');

  const onChange = (newVal: string) => {
    action('change')(newVal);
    setValue(newVal);
  };

  return (
    <TextInput id="storybook-required" label="Name" value={value} required placeholder="Jeff" onChange={onChange} />
  );
};

export const WithIcon = () => {
  const [value, setValue] = useState<string>('');

  const onChange = (newVal: string) => {
    action('change')(newVal);
    setValue(newVal);
  };

  return (
    <TextInput id="storybook-with-icon" label="Name" value={value} placeholder="Jeff" icon="User" onChange={onChange} />
  );
};

export const Error = () => {
  const [value, setValue] = useState<string>('098766');

  const onChange = (newVal: string) => {
    action('change')(newVal);
    setValue(newVal);
  };

  return (
    <TextInput
      id="storybook-error"
      label="My Phone Number"
      value={value}
      error="That's not my number!"
      onChange={onChange}
    />
  );
};
