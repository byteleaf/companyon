import React from 'react';
import Avatar from '../../Atoms/Avatar/Avatar';
import Button, { ButtonColors } from '../../Atoms/Button/Button';
import Heading from './Heading';

const actions = [
  <Button key="phone" prefixIcon="Phone" color={ButtonColors.White}>
    Phone
  </Button>,
  <Button key="mail" prefixIcon="Mail" color={ButtonColors.White}>
    Mail
  </Button>,
];

export default {
  component: Heading,
  title: 'Molecules/Heading',
  parameters: {},
};

const image = <Avatar />;

export const Default = () => <Heading title="Title" subtitle="Subtitle" />;

export const TitleOnly = () => <Heading title="Title" />;

export const WithActions = () => <Heading title="Title" subtitle="Subtitle" actions={actions} />;

export const WithImage = () => <Heading title="Title" subtitle="Subtitle" image={image} />;

export const WithImageAndActions = () => <Heading title="Title" subtitle="Subtitle" image={image} actions={actions} />;
