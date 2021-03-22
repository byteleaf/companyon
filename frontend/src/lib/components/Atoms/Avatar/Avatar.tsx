import React from 'react';
import { useTranslation } from 'react-i18next';
import styled from 'styled-components';
import tw from 'twin.macro';

const Img = styled.img`
  ${tw`inline-block h-10 w-10 rounded-full`}
`;

interface AvatarProps {
  imgSrc?: string | null;
  alt?: string;
}

const Avatar: React.FC<AvatarProps> = ({ imgSrc = '/img/avatar_placeholder.png', alt }) => {
  const { t } = useTranslation();
  let altText = alt;

  if (!altText) {
    altText = t('components.atoms.avatar.alt');
  }

  return <Img src={imgSrc || undefined} alt={altText} />;
};

export default Avatar;
