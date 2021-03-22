import { MockedProvider } from '@apollo/client/testing';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import React from 'react';
import AddCompanyBar from './AddCompanyBar';
import { companies, mocks } from './__mocks__/companyData';

describe('AddCompanyBar', () => {
  beforeEach(() => {
    render(
      <MockedProvider mocks={mocks}>
        <AddCompanyBar />
      </MockedProvider>,
    );
  });

  it('should create a company', async () => {
    const companyNameInput = screen.getByRole('textbox');

    userEvent.type(companyNameInput, companies[0].name);
    userEvent.click(screen.getByRole('button'));

    await waitFor(() => expect(companyNameInput).toHaveValue(''));
    expect(screen.queryByRole('alert')).not.toBeInTheDocument();
  });

  it('should display an error', async () => {
    const companyNameInput = screen.getByRole('textbox');

    userEvent.type(companyNameInput, companies[1].name);
    userEvent.click(screen.getByRole('button'));

    const errorMessage = await screen.findByRole('alert');

    expect(companyNameInput).toHaveValue(companies[1].name);

    expect(errorMessage).toHaveTextContent(`${companies[1].name} already exists`);
  });
});
