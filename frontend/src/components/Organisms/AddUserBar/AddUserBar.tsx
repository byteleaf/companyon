import React, { useState } from 'react';
import { useTranslation } from 'react-i18next';
import styled from 'styled-components';
import tw from 'twin.macro';

import Button, { ButtonTypes } from '../../../lib/components/Atoms/Button/Button';
import Icon from '../../../lib/components/Atoms/Icons/Icon';
import TextInput from '../../../lib/components/Molecules/Input/TextInput/TextInput';
import Bar from '../../../lib/components/Atoms/Bar/Bar';
import { useCreateUserMutation } from '../../../graphql/mutations/__generated__/createUser';

const Form = styled.form`
  ${tw`flex items-end`}
`;

const MiddleInput = styled(TextInput)`
  ${tw`ml-4`}
`;

const LastInput = styled(TextInput)`
  ${tw`ml-4 mr-auto`}
`;

interface Props {
  onError?: (error: Error) => void;
}

const AddUserBar: React.FC<Props> = ({ onError }) => {
  const { t } = useTranslation();

  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');

  const [error, setError] = useState<string | null>(null);

  const handleReset = () => {
    setFirstName('');
    setLastName('');
    setEmail('');
  };

  const [createCompany, { loading }] = useCreateUserMutation({
    onCompleted: () => handleReset(),
    onError: (err) => {
      if (onError) onError(err);
      setError(err.message);
    },
  });

  const addUser = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!firstName || !lastName || !email) return;
    createCompany({ variables: { input: { firstName, lastName, email } } });
  };

  return (
    <Bar>
      <Form onSubmit={addUser}>
        <TextInput
          id="user-first"
          label={t('components.organisms.addUserBar.firstName')}
          value={firstName}
          onChange={setFirstName}
          error={error ? t(error, { object: firstName }) : null}
          required
        />
        <MiddleInput
          id="user-last"
          label={t('components.organisms.addUserBar.lastName')}
          value={lastName}
          onChange={setLastName}
          error={error ? t(error, { object: lastName }) : null}
          required
        />
        <LastInput
          id="user-email"
          label={t('components.organisms.addUserBar.email')}
          value={email}
          onChange={setEmail}
          error={error ? t(error, { object: email }) : null}
          required
        />
        <Button type={ButtonTypes.Submit} disabled={!firstName || !lastName || !email || loading}>
          <Icon icon="Plus" color="primary" colorCharacter="inverse" />
        </Button>
      </Form>
    </Bar>
  );
};

export default AddUserBar;
