import React, { useEffect, useRef, useState } from 'react';
import styled from 'styled-components';
import tw from 'twin.macro';
import TextButton from '../../Atoms/Button/TextButton';
import TextInput from '../../Molecules/Input/TextInput/TextInput';

const Flex = styled.div`
  ${tw`flex justify-between items-center h-12`}
`;

const EndButton = styled(TextButton)`
  ${tw`ml-4`}
`;

const Value = styled.div`
  ${tw`whitespace-no-wrap text-sm text-gray-900`}
`;

type EditableFieldProps = {
  id: string;
  field: string;
  value: string;
  editDisabled?: boolean;
  editHidden?: boolean;
  updating?: boolean;
  onConfirm: (value: { [key: string]: string }) => void;
};

const EditableField: React.FC<EditableFieldProps> = ({
  id,
  field,
  value,
  onConfirm,
  editDisabled,
  editHidden,
  updating,
}) => {
  const previousValue = useRef(value);

  const [fieldValue, setFieldValue] = useState<string>(value);
  const [editing, setEditing] = useState<boolean>(false);

  useEffect(() => {
    if (value !== previousValue.current) {
      setFieldValue(value);
    }
  }, [value]);

  if (updating) {
    return <Value>Updating...</Value>;
  }

  if (editing) {
    return (
      <Flex>
        <TextInput id={id} value={fieldValue} onChange={setFieldValue} />
        <Flex>
          <TextButton
            disabled={fieldValue === value}
            onClick={() => {
              onConfirm({ [field]: fieldValue });
              setFieldValue(value);
              setEditing(false);
            }}
          >
            Confirm
          </TextButton>
          <EndButton
            onClick={() => {
              setFieldValue(value);
              setEditing(false);
            }}
          >
            Cancel
          </EndButton>
        </Flex>
      </Flex>
    );
  }

  return (
    <Flex>
      <Value>{fieldValue}</Value>
      {!editHidden && (
        <TextButton disabled={editDisabled} onClick={() => setEditing(true)}>
          Edit
        </TextButton>
      )}
    </Flex>
  );
};

export default EditableField;
