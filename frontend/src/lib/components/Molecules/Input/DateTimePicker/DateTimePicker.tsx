import React from 'react';
import DatePicker from 'react-datepicker';
import { useTranslation } from 'react-i18next';
import styled from 'styled-components';
import tw from 'twin.macro';
import Icon from '../../../Atoms/Icons/Icon';

interface DateTimePickerProps {
  value: Date | null;
  id: string;
  label: string;
  error?: string | null;
  required?: boolean;
  className?: string;
  onChange: (value: Date) => void;
}

interface DatePickerInputProps {
  value?: string;
  error?: string | null;
  onClick?: (event: React.MouseEvent<HTMLInputElement>) => void;
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const DatePickerInputWrapper = styled.div`
  ${tw`mt-1 relative rounded-md shadow-sm`}
`;

const Input = styled.input<{ error?: string | null }>`
  ${tw`form-input block w-full sm:text-sm sm:leading-5`}

  ${({ error }) =>
    error && tw`pr-10 border-red-300 text-red-900 placeholder-red-300 focus:border-red-300 focus:shadow-outline-red`}
`;

const InputError = styled.div`
  ${tw`absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none`}
`;

// eslint-disable-next-line react/prefer-stateless-function
class DatePickerInput extends React.Component<DatePickerInputProps> {
  render() {
    const { value, error, onClick, onChange } = this.props;

    return (
      <DatePickerInputWrapper>
        <Input error={error} onClick={onClick} onChange={onChange} value={value} />
        {error && (
          <InputError>
            <Icon icon="ExclamationCircle" inputIcon color="alert" />
          </InputError>
        )}
      </DatePickerInputWrapper>
    );
  }
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

const DateTimePickerError = styled.p`
  ${tw`mt-1 text-sm text-red-600`}
`;

const DateTimePicker: React.FC<DateTimePickerProps> = ({ value, id, label, error, required, className, onChange }) => {
  const { t } = useTranslation();

  return (
    <div className={className}>
      <Flex>
        <Label htmlFor={`datetimepicker-${id}`}>{label}</Label>
        {required && <Hint>{t('components.molecules.input.required')}</Hint>}
      </Flex>
      <DatePicker
        id={`datetimepicker-${id}`}
        selected={value}
        onChange={onChange}
        showTimeSelect
        timeFormat="HH:mm"
        timeIntervals={15}
        timeCaption="Time"
        dateFormat="dd.MM.yyyy HH:mm"
        customInput={<DatePickerInput error={error} />}
      />
      {error && <DateTimePickerError>{error}</DateTimePickerError>}
    </div>
  );
};

export default DateTimePicker;
