import React from 'react';
import styled from 'styled-components';
import tw from 'twin.macro';
import LoadingSkeleton from './LoadingSkeleton';
import { backgrounds } from '../../../../../.storybook/preview';

export default {
  component: LoadingSkeleton,
  title: 'Atoms/Loading Skeleton',
  parameters: {
    backgrounds,
  },
};

const BlockLoadingSkeleton = styled(LoadingSkeleton)`
  ${tw`w-48 h-64`};
`;

export const Block = () => <BlockLoadingSkeleton />;

export const Inline = () => <LoadingSkeleton inline className="w-20" />;
