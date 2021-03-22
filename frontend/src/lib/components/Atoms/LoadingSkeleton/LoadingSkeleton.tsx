import React from 'react';
import styled, { keyframes } from 'styled-components';
import tw from 'twin.macro';

interface LoadingSkeletonProps {
  className?: string;
  inline?: boolean;
}

const shimmer = keyframes`
  100% {
    transform: translateX(100%);
  }
`;

const Span = styled.span<{ inline: boolean }>`
  ${tw`relative rounded overflow-hidden bg-gray-300`}
  ${({ inline }) => (inline ? tw`inline-block h-5` : tw`block h-full`)}

  &::after {
    ${tw`absolute inset-0`}

    transform: translateX(-100%);
    background-image: linear-gradient(
      90deg,
      rgba(255, 255, 255, 0) 0,
      rgba(255, 255, 255, 0.2) 20%,
      rgba(255, 255, 255, 0.5) 60%,
      rgba(255, 255, 255, 0)
    );
    animation: ${shimmer} 2s infinite;
    content: '';
  }
`;

const LoadingSkeleton: React.FC<LoadingSkeletonProps> = ({ className, inline }) => (
  <Span inline={inline || false} className={className} />
);

export default LoadingSkeleton;
