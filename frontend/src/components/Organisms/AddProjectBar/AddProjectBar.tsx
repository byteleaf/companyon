import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import styled from 'styled-components';
import tw from 'twin.macro';
import { useCompaniesQuery } from '../../../graphql/queries/__generated__/companies';
import { useCreateProjectMutation } from '../../../graphql/mutations/__generated__/createProject';
import { KeyValuePair } from '../../../types';
import Bar from '../../../lib/components/Atoms/Bar/Bar';
import Button, { ButtonTypes } from '../../../lib/components/Atoms/Button/Button';
import Icon from '../../../lib/components/Atoms/Icons/Icon';
import Dropdown from '../../../lib/components/Molecules/Input/Dropdown/Dropdown';
import TextInput from '../../../lib/components/Molecules/Input/TextInput/TextInput';

const Form = styled.form`
  ${tw`flex items-end`}
`;

const margin = tw`ml-4 mr-auto`;

const MarginLoading = styled.span`
  ${margin}
`;

const MarginDropdown = styled(Dropdown)`
  ${margin}
`;

interface Props {
  onError?: (error: Error) => void;
}

const AddProjectBar: React.FC<Props> = ({ onError }) => {
  const { t } = useTranslation();

  const [projectName, setProjectName] = useState('');
  const [companyId, setCompanyId] = useState('');
  const [companies, setCompanies] = useState<KeyValuePair<string>[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [mutation] = useCreateProjectMutation({
    variables: {
      input: {
        name: projectName,
        company: companyId,
      },
    },
  });

  const { data, loading: companiesLoading } = useCompaniesQuery();

  useEffect(() => {
    if (!data) {
      setCompanies([]);
      return;
    }

    setCompanies(data.companies.map(({ id, name }) => ({ key: id, value: name })));
  }, [data]);

  const addProject = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      setError(null);
      await mutation();

      setProjectName('');
      setCompanyId('');
    } catch (err) {
      setError(err?.graphQLErrors?.[0]?.extensions?.i18n ?? err.message);

      if (onError) {
        onError(err);
      }
    }
  };

  return (
    <Bar>
      <Form onSubmit={addProject}>
        <TextInput
          id="project-name"
          label={t('components.organisms.addProjectBar.projectName')}
          value={projectName}
          onChange={setProjectName}
          error={error && t(error)}
          required
        />

        {!companiesLoading && data ? (
          <MarginDropdown
            id="company-name"
            label={t('components.organisms.addProjectBar.companyName')}
            value={companyId}
            onChange={setCompanyId}
            items={companies}
            error={error && t(error)}
            required
          />
        ) : (
          <MarginLoading>{t('components.organisms.addProjectBar.companiesLoading')}</MarginLoading>
        )}

        <Button type={ButtonTypes.Submit} disabled={!projectName.trim() && !companyId}>
          <Icon icon="Plus" color="primary" colorCharacter="inverse" />
        </Button>
      </Form>
    </Bar>
  );
};

export default AddProjectBar;
