import React from 'react';
import { IconProps } from './IconProps';

function ArrowNarrowLeftIcon({ className }: IconProps) {
  return (
    <svg fill="none" viewBox="0 0 24 24" stroke="currentColor" className={className}>
      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M7 16l-4-4m0 0l4-4m-4 4h18" />
    </svg>
  );
}

export default ArrowNarrowLeftIcon;
