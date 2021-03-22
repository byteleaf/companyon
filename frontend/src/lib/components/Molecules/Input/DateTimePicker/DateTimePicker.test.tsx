import React, { useState } from 'react';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import DateTimePicker from './DateTimePicker';

const WrappedPicker = ({ startDate = new Date(2019, 8, 7, 6, 5, 4) }: { startDate?: Date }) => {
  const [value, setValue] = useState(startDate);

  return <DateTimePicker value={value} id="test" label="test" onChange={setValue} />;
};
WrappedPicker.defaultProps = {
  startDate: new Date(2019, 8, 7, 6, 5, 4),
};

describe('DateTimePicker', () => {
  it('should render', async () => {
    render(<WrappedPicker />);

    const datebox = screen.getByRole('textbox');

    expect(datebox).toHaveValue('07.09.2019 06:05');
  });

  it('should change date', async () => {
    render(<WrappedPicker />);

    const datebox = screen.getByRole('textbox');

    userEvent.click(datebox);

    const newDate = await screen.findByRole('button', { name: /choose wednesday, september 18th, 2019/i });

    userEvent.click(newDate);

    const newTime = screen.getByText(/23:45/);
    userEvent.click(newTime);

    expect(datebox).toHaveValue('18.09.2019 23:45');
  });
});
