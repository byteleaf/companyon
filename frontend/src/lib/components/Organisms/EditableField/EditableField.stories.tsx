import { action } from '@storybook/addon-actions';
import React, { useState } from 'react';
import EditableField from './EditableField';

export default {
  component: EditableField,
  title: 'Organisms/EditableField',
};

const user = { firstName: 'Jeff' };
const field = 'firstName';

export const Default = () => {
  const [value, setValue] = useState<{ [key: string]: string }>(user);

  const onConfirm = (newVal: { [key: string]: string }) => {
    action('change')(newVal);
    setValue(newVal);
  };

  return <EditableField id="editable-field" field={field} value={value.firstName} onConfirm={onConfirm} />;
};

export const Disabled = () => {
  const [value, setValue] = useState<{ [key: string]: string }>(user);

  const onConfirm = (newVal: { [key: string]: string }) => {
    action('change')(newVal);
    setValue(newVal);
  };

  return <EditableField editDisabled id="editable-field" field={field} value={value.firstName} onConfirm={onConfirm} />;
};

export const Hidden = () => {
  const [value, setValue] = useState<{ [key: string]: string }>(user);

  const onConfirm = (newVal: { [key: string]: string }) => {
    action('change')(newVal);
    setValue(newVal);
  };

  return <EditableField editHidden id="editable-field" field={field} value={value.firstName} onConfirm={onConfirm} />;
};
