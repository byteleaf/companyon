import React from 'react';
import styled from 'styled-components';
import tw from 'twin.macro';
import { format } from 'date-fns';
import Tag from '../../../lib/components/Atoms/Tag/Tag';
import Icon from '../../../lib/components/Atoms/Icons/Icon';
import { TimeLoggingEntryType } from './types';

const Wrapper = styled.div`
  ${tw`px-4 py-4 sm:px-6`};
`;

const Header = styled.div`
  ${tw`flex items-center justify-between text-sm leading-5 font-medium text-indigo-600`};
`;

const TruncatedText = styled.div`
  ${tw`truncate`};
`;

const AdditionalInformations = styled.div`
  ${tw`mt-2 sm:flex sm:justify-between`};
`;

const Tags = styled.div`
  ${tw`sm:flex`};
`;

const TagWrapper = styled.div`
  ${tw`mr-2 flex items-center text-sm leading-5 text-gray-500`};
`;

const DateWrapper = styled.div`
  ${tw`mt-2 flex items-center text-sm leading-5 text-gray-500 sm:mt-0`};
`;

const Label = styled.label`
  ${tw`block text-sm font-medium leading-5 text-gray-700`}
`;

const TimeLoggingEntry: React.FC<TimeLoggingEntryType> = ({ title, time, date, tags, project, company }) => (
  <Wrapper>
    <Header>
      <TruncatedText>{title}</TruncatedText>
      <div>{time}</div>
    </Header>
    <AdditionalInformations>
      <Label>{`${project} - ${company}`}</Label>
      <Tags>
        {tags.map(({ id, value, hexColor, outline }) => (
          <TagWrapper key={id}>
            <Tag hexColor={hexColor} outline={outline}>
              {value}
            </Tag>
          </TagWrapper>
        ))}
      </Tags>
      <DateWrapper>
        <Icon icon="Calendar" colorCharacter="inverse" className="mr-1.5" />
        <span>
          <time dateTime={date}>{format(new Date(date), 'MMMM d, yyyy')}</time>
        </span>
      </DateWrapper>
    </AdditionalInformations>
  </Wrapper>
);

export default TimeLoggingEntry;
