import React from 'react';
import styled from 'styled-components';
import tw from 'twin.macro';
import Heading from '../../Molecules/Heading/Heading';
import { DetailViewConfig } from './types';

const DetailViewWrapper = styled.div`
  ${tw`bg-white shadow overflow-hidden sm:rounded-lg`}
`;

const DetailViewBodyWrapper = styled.div`
  ${tw`px-4 py-5 sm:p-0`}
`;

const DetailViewBody = styled.dl``;

const RowWrapper = styled.div`
  ${tw`items-center first:mt-0 mt-8 sm:mt-0 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6 sm:py-5 sm:first:border-t-0 sm:border-t sm:border-gray-200`}
`;

const RowLabel = styled.dt`
  ${tw`text-sm leading-5 font-medium text-gray-500`}
`;

const RowValue = styled.dd`
  ${tw`mt-1 text-sm leading-5 text-gray-900 sm:mt-0 sm:col-span-2`}
`;

interface DetailViewRowProps {
  label: string;
}

const DetailViewRow: React.FC<DetailViewRowProps> = ({ children, label }) => (
  <RowWrapper>
    <RowLabel>{label}</RowLabel>
    <RowValue>{children}</RowValue>
  </RowWrapper>
);

interface DetailViewProps {
  config: DetailViewConfig;
}

const DetailView: React.FC<DetailViewProps> = ({ config: { header, rows } }) => (
  <DetailViewWrapper>
    <Heading {...header} />

    <DetailViewBodyWrapper>
      <DetailViewBody>
        {rows.map(({ key, value }) => (
          <DetailViewRow key={key} label={key}>
            {value}
          </DetailViewRow>
        ))}
      </DetailViewBody>
    </DetailViewBodyWrapper>
  </DetailViewWrapper>
);

export default DetailView;
