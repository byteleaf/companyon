import React from 'react';
import styled from 'styled-components';
import tw from 'twin.macro';

const HeaderContainer = styled.div`
  ${tw`bg-white px-4 py-5 border-b border-gray-200 sm:px-6`}
`;

const HeaderContent = styled.div`
  ${tw`-ml-4 -mt-4 flex justify-between items-center flex-wrap sm:flex-no-wrap`}
`;

const ImageTextWrapper = styled.div`
  ${tw`ml-4 mt-4 flex items-center`}
`;

const ImageWrapper = styled.div`
  ${tw`flex-shrink-0`}
`;

const TextWrapper = styled.div<{ hasImage: boolean }>`
  ${({ hasImage }) => hasImage && tw`ml-4`}
`;

const Title = styled.h3`
  ${tw`text-lg leading-6 font-medium text-gray-900`}
`;

const Subtitle = styled.p`
  ${tw`text-sm leading-5 text-gray-500`}
`;

const ActionsContainer = styled.div`
  ${tw`ml-4 mt-4 flex-shrink-0 flex`}
`;

const ActionWrapper = styled.span`
  ${tw`first:ml-0 ml-3 inline-flex rounded-md shadow-sm`}
`;

interface HeadingProps {
  title: string;
  subtitle?: string;
  image?: JSX.Element;
  actions?: React.ReactElement[];
}

const Heading: React.FC<HeadingProps> = ({ image, title, subtitle, actions = [] }) => {
  return (
    <HeaderContainer>
      <HeaderContent>
        <ImageTextWrapper>
          {image && <ImageWrapper>{image}</ImageWrapper>}

          <TextWrapper hasImage={!!image}>
            <Title>{title}</Title>
            {subtitle && <Subtitle>{subtitle}</Subtitle>}
          </TextWrapper>
        </ImageTextWrapper>
        <ActionsContainer>
          {actions.map((action) => (
            <ActionWrapper key={action.key}>{action}</ActionWrapper>
          ))}
        </ActionsContainer>
      </HeaderContent>
    </HeaderContainer>
  );
};

export default Heading;
