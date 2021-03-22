import React from 'react';
import styled from 'styled-components';
import tw from 'twin.macro';
import Icons from './components';
import { theme, ThemeColors, ThemeColorCharacters } from '../../../../theme';

export interface IconProps {
  icon: keyof typeof Icons;
  inputIcon?: boolean;
  className?: string;
  color?: ThemeColors;
  colorCharacter?: ThemeColorCharacters;
}

export const StyledIcon = styled.div<Pick<IconProps, 'inputIcon' | 'color' | 'colorCharacter'>>`
  svg {
    ${({ inputIcon }) => (inputIcon ? tw`h-5 w-5` : tw`h-6 w-6`)};
    ${({ color = 'gray', colorCharacter = 'default' }) => theme.colors[color][colorCharacter]};
  }
`;

const Icon: React.FC<IconProps> = ({ icon, inputIcon, color, colorCharacter, className }) => {
  const SelectedIcon = Icons[icon];

  return (
    <StyledIcon inputIcon={inputIcon} color={color} colorCharacter={colorCharacter} className={className}>
      <SelectedIcon />
    </StyledIcon>
  );
};

export default Icon;
