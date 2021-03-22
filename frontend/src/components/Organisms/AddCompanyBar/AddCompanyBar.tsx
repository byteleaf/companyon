import React, { useState } from 'react';
import { useTranslation } from 'react-i18next';
import styled from 'styled-components';
import tw from 'twin.macro';

import Button, { ButtonTypes } from '../../../lib/components/Atoms/Button/Button';
import Icon from '../../../lib/components/Atoms/Icons/Icon';
import TextInput from '../../../lib/components/Molecules/Input/TextInput/TextInput';
import { useCreateCompanyMutation } from '../../../graphql/mutations/__generated__/createCompany';
import Bar from '../../../lib/components/Atoms/Bar/Bar';

const Form = styled.form`
  ${tw`flex justify-between items-end`}
`;

interface Props {
  onError?: (error: Error) => void;
}

const AddCompanyBar: React.FC<Props> = ({ onError }) => {
  const { t } = useTranslation();

  const [name, setName] = useState('');
  const [error, setError] = useState<string | null>(null);

  const [createCompany, { loading }] = useCreateCompanyMutation({
    onCompleted: () => setName(''),
    onError: (err) => {
      if (onError) onError(err);
      setError(err.message);
    },
  });

  const addCompany = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!name) return;
    createCompany({ variables: { input: { name } } });
  };

  return (
    <Bar>
      <Form onSubmit={addCompany}>
        <TextInput
          id="company-name"
          label={t('components.organisms.addCompanyBar.companyName')}
          value={name}
          onChange={setName}
          error={error ? t(error, { object: name }) : null}
        />
        <Button type={ButtonTypes.Submit} disabled={!name || loading}>
          <Icon icon="Plus" color="primary" colorCharacter="inverse" />
        </Button>
      </Form>
    </Bar>
  );
};

export default AddCompanyBar;
