import React from 'react';
import styled, { css } from 'styled-components';
import tw from 'twin.macro';
import Icon from '../Icons/Icon';
import Icons from '../Icons/components';

export enum ButtonTypes {
  Submit = 'submit',
  Reset = 'reset',
  Button = 'button',
}

export enum ButtonColors {
  Primary = 'primary',
  Secondary = 'secondary',
  White = 'white',
}

const PrefixIcon = styled(Icon)`
  ${tw`-ml-1 mr-2`}
`;

const SuffixIcon = styled(Icon)`
  ${tw`-mr-1 ml-2`}
`;

const StyledButton = styled.button<{ color?: ButtonColors }>`
  ${tw`inline-flex items-center h-9_5 px-4 py-2 border text-sm leading-5 font-medium rounded-md focus:outline-none transition ease-in-out duration-150`};

  ${({ color }) => {
    switch (color) {
      case ButtonColors.Secondary:
        return css`
          ${tw`border-transparent text-indigo-700 bg-indigo-100 hover:bg-indigo-50 focus:border-indigo-300 focus:shadow-outline-indigo active:bg-indigo-200`};
        `;
      case ButtonColors.White:
        return css`
          ${tw`border-gray-300 text-gray-700 bg-white hover:text-gray-500 focus:border-blue-300 focus:shadow-outline-blue active:text-gray-800 active:bg-gray-50`};
        `;
      default:
        return css`
          ${tw`border-transparent text-white bg-indigo-600 hover:bg-indigo-500 focus:border-indigo-700 focus:shadow-outline-indigo active:bg-indigo-700`};
        `;
    }
  }}

  ${({ disabled }) =>
    disabled &&
    css`
      ${tw`opacity-50 pointer-events-none`}
    `}
`;

interface ButtonProps {
  type?: ButtonTypes;
  prefixIcon?: keyof typeof Icons;
  suffixIcon?: keyof typeof Icons;
  color?: ButtonColors;
  onClick?: () => void;
  className?: string;
  disabled?: boolean;
}

const Button: React.FC<ButtonProps> = ({
  type = ButtonTypes.Button,
  children,
  prefixIcon,
  suffixIcon,
  onClick,
  className,
  color = ButtonColors.Primary,
  disabled,
}) => {
  return (
    <StyledButton type={type} color={color} onClick={onClick} className={className} disabled={disabled}>
      {prefixIcon && <PrefixIcon icon={prefixIcon} inputIcon color={color} colorCharacter="inverse" />}
      {children}
      {suffixIcon && <SuffixIcon icon={suffixIcon} inputIcon color={color} colorCharacter="inverse" />}
    </StyledButton>
  );
};

export default Button;
