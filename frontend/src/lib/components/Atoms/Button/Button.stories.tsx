import React from 'react';
import { action } from '@storybook/addon-actions';
import Button, { ButtonColors, ButtonTypes } from './Button';
import TextButton from './TextButton';

export default {
  component: Button,
  title: 'Atoms/Button',
};

export const Primary = () => {
  return <Button>Click me!</Button>;
};

export const Secondary = () => {
  return <Button color={ButtonColors.Secondary}>Click me!</Button>;
};

export const White = () => {
  return <Button color={ButtonColors.White}>Click me!</Button>;
};

export const Disabled = () => {
  return (
    <>
      <div className="mb-4">
        <Button disabled prefixIcon="Briefcase">
          Click me!
        </Button>
      </div>
      <div className="mb-4">
        <Button disabled prefixIcon="Briefcase" color={ButtonColors.Secondary}>
          Click me!
        </Button>
      </div>
      <div>
        <Button disabled prefixIcon="Briefcase" color={ButtonColors.White}>
          Click me!
        </Button>
      </div>
    </>
  );
};

export const PrefixIcon = () => {
  return (
    <>
      <div className="mb-4">
        <Button prefixIcon="Briefcase">Click me!</Button>
      </div>
      <div className="mb-4">
        <Button prefixIcon="Briefcase" color={ButtonColors.Secondary}>
          Click me!
        </Button>
      </div>
      <div>
        <Button prefixIcon="Briefcase" color={ButtonColors.White}>
          Click me!
        </Button>
      </div>
    </>
  );
};

export const SuffixIcon = () => {
  return (
    <>
      <div className="mb-4">
        <Button suffixIcon="Briefcase">Click me!</Button>
      </div>
      <div className="mb-4">
        <Button suffixIcon="Briefcase" color={ButtonColors.Secondary}>
          Click me!
        </Button>
      </div>
      <div>
        <Button suffixIcon="Briefcase" color={ButtonColors.White}>
          Click me!
        </Button>
      </div>
    </>
  );
};

export const Submit = () => {
  return <Button type={ButtonTypes.Submit}>Click me!</Button>;
};

export const WithOnClickEvent = () => {
  return <Button onClick={action('Button clicked!')}>Click me!</Button>;
};

export const Text = () => {
  return <TextButton onClick={action('Button clicked!')}>Click me!</TextButton>;
};
