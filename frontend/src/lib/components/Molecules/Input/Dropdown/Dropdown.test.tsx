import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import React, { useState } from 'react';
import Dropdown from './Dropdown';

const WrappedDropdown = () => {
  const [value, setValue] = useState('');

  return (
    <>
      <Dropdown
        value={value}
        id="test"
        label="test"
        onChange={setValue}
        items={[
          { key: 'key_alpha', value: 'Alpha' },
          { key: 'key_beta', value: 'Beta' },
          { key: 'key_gamma', value: 'Gamma' },
          { key: 'key_beta2', value: 'Beta2' },
        ]}
      />

      <span className="result">{value}</span>
    </>
  );
};

describe('Dropdown', () => {
  it('should render', () => {
    render(<WrappedDropdown />);
  });

  it('should select correct value (via keyboard)', () => {
    render(<WrappedDropdown />);

    const inputBox = screen.getByRole('combobox');

    userEvent.type(inputBox, 'bet{arrowdown}{enter}');

    screen.getByText('key_beta');
  });

  it('should select correct value (via mouse)', () => {
    render(<WrappedDropdown />);

    const inputBox = screen.getByRole('combobox');

    userEvent.type(inputBox, 'bet');

    userEvent.click(screen.getByRole('option', { name: 'Beta' }));

    screen.getByText('key_beta');
  });
});
