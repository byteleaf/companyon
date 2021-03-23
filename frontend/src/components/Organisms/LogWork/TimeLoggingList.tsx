import React from 'react';
import styled from 'styled-components';
import tw from 'twin.macro';
import TimeLoggingEntry from './TimeLoggingEntry';
import { TimeLoggingEntryType } from './types';

interface TimeLoggingListProps {
  entries: TimeLoggingEntryType[];
}

const Wrapper = styled.div`
  ${tw`bg-white shadow overflow-hidden sm:rounded-md`};
`;

const A = styled.a`
  ${tw`border-b border-gray-200 block hover:bg-gray-50 focus:outline-none focus:bg-gray-50 transition duration-150 ease-in-out`};
`;

const TimeLoggingList: React.FC<TimeLoggingListProps> = ({ entries }) => {
  return (
    <Wrapper>
      <ul>
        {entries.map((entry) => (
          <li key={entry.id}>
            <A>
              <TimeLoggingEntry {...entry} />
            </A>
          </li>
        ))}
      </ul>
    </Wrapper>
  );
};

export default TimeLoggingList;
