import React from 'react';
import Avatar from '../../Atoms/Avatar/Avatar';
import Button, { ButtonColors } from '../../Atoms/Button/Button';
import DetailView from './DetailView';

const detailViewConfig = {
  header: {
    title: 'Title',
    subtitle: 'Subtitle',
  },
  rows: [
    { key: 'key1', value: 'value1' },
    { key: 'key2', value: 'value2' },
    { key: 'key3', value: 'value3' },
    { key: 'key4', value: 'value4' },
  ],
};

const detailViewConfigWithAvatar = { ...detailViewConfig, header: { ...detailViewConfig.header, image: <Avatar /> } };

const detailViewConfigWithAvatarAndActions = {
  ...detailViewConfigWithAvatar,
  header: {
    ...detailViewConfigWithAvatar.header,
    actions: [
      <Button key="action1" color={ButtonColors.White} prefixIcon="Adjustments">
        Action 1
      </Button>,
      <Button key="action2" color={ButtonColors.White} prefixIcon="Annotation">
        Action 2
      </Button>,
    ],
  },
};

export default {
  component: DetailView,
  title: 'Organisms/DetailView',
};

export const Default = () => <DetailView config={detailViewConfig} />;

export const WithAvatar = () => <DetailView config={detailViewConfigWithAvatar} />;

export const WithAvatarAndActions = () => <DetailView config={detailViewConfigWithAvatarAndActions} />;
