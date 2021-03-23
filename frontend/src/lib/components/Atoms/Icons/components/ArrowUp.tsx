import React from 'react';
import { IconProps } from './IconProps';

function ArrowUpIcon({ className }: IconProps) {
  return (
    <svg fill="none" viewBox="0 0 24 24" stroke="currentColor" className={className}>
      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 10l7-7m0 0l7 7m-7-7v18" />
    </svg>
  );
}

export default ArrowUpIcon;
