import React, { useState, useEffect } from 'react';
import { Combobox, ComboboxInput, ComboboxPopover, ComboboxList, ComboboxOption } from '@reach/combobox';
import { matchSorter } from 'match-sorter';
import styled from 'styled-components';
import tw from 'twin.macro';
import { useTranslation } from 'react-i18next';
import { KeyValuePair } from '../../../../../types';
import Icon from '../../../Atoms/Icons/Icon';

const useMatch = (items: KeyValuePair<string>[], term: string) =>
  term.trim() === '' ? items : matchSorter(items, term, { keys: ['value'] });

interface DropdownProps {
  id: string;
  label: string;
  items: KeyValuePair<string>[];
  value: string;
  error?: string | null;
  required?: boolean;
  onChange: (value: string) => void;
  className?: string;
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

const ComboboxWrapper = styled.div`
  ${tw`mt-1 relative rounded-md shadow-sm`}
`;

const StyledComboboxInput = styled(ComboboxInput)<{ error?: string | null }>`
  ${tw`form-input block w-full sm:text-sm sm:leading-5`}

  ${({ error }) =>
    error && tw`pr-10 border-red-300 text-red-900 placeholder-red-300 focus:border-red-300 focus:shadow-outline-red`}
`;

const ErrorIconWrapper = styled.div`
  ${tw`absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none`}
`;

const Error = styled.p`
  ${tw`mt-1 text-sm text-red-600`}
`;

const StyledComboboxPopover = styled(ComboboxPopover)`
  &[data-reach-combobox-popover] {
    border: solid 1px hsla(0, 0%, 0%, 0.25);
    background: hsla(0, 100%, 100%, 0.99);
    font-size: 85%;
  }

  [data-reach-combobox-list] {
    list-style: none;
    margin: 0;
    padding: 0;
    user-select: none;
  }

  [data-reach-combobox-option] {
    cursor: pointer;
    margin: 0;
    padding: 0.25rem 0.5rem;
  }

  [data-reach-combobox-option][aria-selected='true'] {
    background: hsl(211, 10%, 95%);
  }

  [data-reach-combobox-option]:hover {
    background: hsl(211, 10%, 92%);
  }

  [data-reach-combobox-option][aria-selected='true']:hover {
    background: hsl(211, 10%, 90%);
  }

  [data-suggested-value] {
    font-weight: bold;
  }
`;

const FallbackText = styled.div`
  ${tw`m-2`}
`;

// TODO: Löschen wenn Suche erfolglos

const Dropdown: React.FC<DropdownProps> = ({ id, label, items, value, error, required, onChange, className }) => {
  const { t } = useTranslation();
  const [term, setTerm] = useState('');
  const [pristine, setPristine] = useState(true);
  const results = useMatch(items, term);

  useEffect(() => {
    setTerm(items.find((i) => i.key === value)?.value || '');
  }, [value, items]);

  const onInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPristine(false);
    setTerm(event.target.value);
  };

  const renderOptions = (renderItems: KeyValuePair<string>[]) =>
    renderItems.map((item) => (
      <ComboboxOption key={item.key} value={item.key} onClick={() => setPristine(true)}>
        {item.value}
      </ComboboxOption>
    ));

  return (
    <Combobox openOnFocus onSelect={onChange} className={className}>
      <Flex>
        <Label>{label}</Label>
        {required && <Hint>{t('components.molecules.input.required')}</Hint>}
      </Flex>
      <ComboboxWrapper>
        <StyledComboboxInput
          id={`dropdown-${id}`}
          value={term}
          onChange={onInputChange}
          onClick={() => setPristine(false)}
          error={error}
          autocomplete={false}
        />
        {error && (
          <ErrorIconWrapper>
            <Icon icon="ExclamationCircle" inputIcon color="alert" />
          </ErrorIconWrapper>
        )}
      </ComboboxWrapper>
      {error && <Error>{error}</Error>}
      {!pristine && (
        <StyledComboboxPopover>
          {results?.length > 0 ? (
            <ComboboxList>{renderOptions(results)}</ComboboxList>
          ) : (
            <FallbackText>{t('components.molecules.input.noData')}</FallbackText>
          )}
        </StyledComboboxPopover>
      )}
    </Combobox>
  );
};

export default Dropdown;
