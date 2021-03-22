import React from 'react';
import Avatar from './Avatar';

export default {
  component: Avatar,
  title: 'Atoms/Avatar',
};

export const Default = () => <Avatar imgSrc="/img/testing_thumb.jpg" />;

export const Placeholder = () => <Avatar />;
