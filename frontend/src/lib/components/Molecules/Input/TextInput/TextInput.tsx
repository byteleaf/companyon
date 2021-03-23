import React, { InputHTMLAttributes } from 'react';
import { useTranslation } from 'react-i18next';
import styled from 'styled-components';
import tw from 'twin.macro';
import Icons from '../../../Atoms/Icons/components';
import Icon from '../../../Atoms/Icons/Icon';

interface TextInputProps {
  id: string;
  label?: string;
  value: string;
  placeholder?: string;
  error?: string | null;
  required?: boolean;
  className?: string;
  type?: InputHTMLAttributes<HTMLInputElement>['type'];
  icon?: keyof typeof Icons;
  onChange: (value: string) => void;
}

const Flex = styled.div`
  ${tw`flex justify-between`}
`;

const Label = styled.label`
  ${tw`block text-sm font-medium leading-5 text-gray-700`}
`;

const Hint = styled.span`
  ${tw`text-sm leading-5 text-gray-500`}
`;

const IconWrapper = styled.div`
  ${tw`absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none`}
`;

const InputWrapper = styled.div`
  ${tw`mt-1 relative rounded-md shadow-sm`}
`;

const Input = styled.input<{ icon?: keyof typeof Icons; error?: string | null }>`
  ${tw`form-input block w-full sm:text-sm sm:leading-5`}

  ${({ icon }) => icon && tw`pl-10`}

  ${({ error }) =>
    error && tw`pr-10 border-red-300 text-red-900 placeholder-red-300 focus:border-red-300 focus:shadow-outline-red`}
`;

const ErrorIconWrapper = styled.div`
  ${tw`absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none`}
`;

const Error = styled.p`
  ${tw`mt-1 text-sm text-red-600`}
`;

const TextInput: React.FC<TextInputProps> = ({
  id,
  label,
  value,
  placeholder,
  icon,
  error,
  required,
  type,
  onChange,
  className,
}) => {
  const { t } = useTranslation();

  return (
    <div className={className}>
      {label && (
        <Flex>
          <Label htmlFor={`text-input-${id}`}>{label}</Label>
          {required && <Hint>{t('components.molecules.input.required')}</Hint>}
        </Flex>
      )}
      <InputWrapper>
        {icon && (
          <IconWrapper>
            <Icon icon={icon} inputIcon color="gray" />
          </IconWrapper>
        )}
        <Input
          id={`text-input-${id}`}
          icon={icon}
          error={error}
          placeholder={placeholder}
          value={value}
          type={type || 'text'}
          onChange={(event) => {
            onChange(event.target.value);
          }}
        />
        {error && (
          <ErrorIconWrapper>
            <Icon icon="ExclamationCircle" inputIcon color="alert" />
          </ErrorIconWrapper>
        )}
      </InputWrapper>
      {error && <Error role="alert">{error}</Error>}
    </div>
  );
};

export default TextInput;
