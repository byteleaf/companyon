import React, { useState } from 'react';
import { action } from '@storybook/addon-actions';
import DateTimePicker from './DateTimePicker';

export default {
  component: DateTimePicker,
  title: 'Molecules/Input/DateTimePicker',
};

export const Default = () => {
  const [value, setValue] = useState<Date | null>(null);

  const onChange = (val: Date) => {
    action('onChange')(val);
    setValue(val);
  };

  return <DateTimePicker id="storybook-default" label="When did it happen?" value={value} onChange={onChange} />;
};

export const Prefilled = () => {
  const defaultDate = new Date('2020-07-10T09:15:00');

  const [value, setValue] = useState<Date | null>(defaultDate);

  const onChange = (val: Date) => {
    action('onChange')(val);
    setValue(val);
  };

  return <DateTimePicker id="storybook-prefilled" label="When did it happen?" value={value} onChange={onChange} />;
};

export const Required = () => {
  const [value, setValue] = useState<Date | null>(null);

  const onChange = (val: Date) => {
    action('onChange')(val);
    setValue(val);
  };

  return (
    <DateTimePicker id="storybook-required" label="When did it happen?" value={value} required onChange={onChange} />
  );
};

export const Error = () => {
  const defaultDate = new Date('2020-07-10T09:15:00');

  const [value, setValue] = useState<Date | null>(defaultDate);

  const onChange = (val: Date) => {
    action('onChange')(val);
    setValue(val);
  };

  return (
    <DateTimePicker
      id="storybook-error"
      label="When did it happen?"
      error="This is an error!"
      value={value}
      onChange={onChange}
    />
  );
};
