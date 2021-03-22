import { MockedProvider } from '@apollo/client/testing';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import React from 'react';
import { companies, mocks, project } from './__mocks__/projectData';
import AddProjectBar from './AddProjectBar';

describe('AddProjectBar', () => {
  beforeEach(() => {
    render(
      <MockedProvider mocks={mocks}>
        <AddProjectBar />
      </MockedProvider>,
    );
  });

  it('should display companies loading state on loading and dropdown on finish', async () => {
    expect(screen.getByText(/loading/i)).toBeInTheDocument();
    expect(await screen.findByRole('combobox')).toBeInTheDocument();
  });

  it('should render with correct tab order', async () => {
    const projectNameInput = screen.getByRole('textbox');
    const companyNameInput = await screen.findByRole('combobox');
    const submitButton = screen.getByRole('button');

    projectNameInput.focus();

    expect(projectNameInput).toHaveFocus();

    userEvent.tab();

    expect(companyNameInput).toHaveFocus();

    userEvent.tab();

    // button is disabled because the inputs are empty
    expect(submitButton).not.toHaveFocus();
  });

  it('should create a company', async () => {
    const projectNameInput = screen.getByRole('textbox');

    userEvent.type(projectNameInput, project.name);

    const companyNameInput = await screen.findByRole('combobox');

    userEvent.type(companyNameInput, `${companies[0].name}{arrowdown}{enter}`);
    userEvent.click(screen.getByRole('button'));

    await waitFor(() => expect(projectNameInput).toHaveValue(''));
    await waitFor(() => expect(companyNameInput).toHaveValue(''));

    expect(screen.queryByRole('alert')).not.toBeInTheDocument();
  });

  it('should display an error', async () => {
    const projectNameInput = screen.getByRole('textbox');

    userEvent.type(projectNameInput, project.name);

    const companyNameInput = await screen.findByRole('combobox');

    userEvent.type(companyNameInput, `${companies[1].name}{arrowdown}{enter}`);

    userEvent.click(screen.getByRole('button'));

    const errorMessage = await screen.findByRole('alert');

    expect(projectNameInput).toHaveValue(project.name);
    expect(companyNameInput).toHaveValue(companies[1].name);

    expect(errorMessage).toHaveTextContent(/already exists/i);
  });
});
