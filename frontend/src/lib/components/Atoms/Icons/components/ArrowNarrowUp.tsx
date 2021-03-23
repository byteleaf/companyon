import React from 'react';
import { IconProps } from './IconProps';

function ArrowNarrowUpIcon({ className }: IconProps) {
  return (
    <svg fill="none" viewBox="0 0 24 24" stroke="currentColor" className={className}>
      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M8 7l4-4m0 0l4 4m-4-4v18" />
    </svg>
  );
}

export default ArrowNarrowUpIcon;
