import React from 'react';
import { IconProps } from './IconProps';

function ArrowNarrowDownIcon({ className }: IconProps) {
  return (
    <svg fill="none" viewBox="0 0 24 24" stroke="currentColor" className={className}>
      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M16 17l-4 4m0 0l-4-4m4 4V3" />
    </svg>
  );
}

export default ArrowNarrowDownIcon;
