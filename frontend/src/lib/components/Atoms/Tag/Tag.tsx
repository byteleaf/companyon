import React from 'react';
import styled, { css } from 'styled-components';
import tw from 'twin.macro';
import { modifyColorLightness, isLightColor } from '../../../../helpers/colorHelpers';

interface TagProps {
  hexColor?: string;
  outline?: boolean;
}

const Span = styled.span<TagProps>`
  ${tw`px-2 inline-flex text-xs leading-5 font-semibold rounded-full border-solid border`};

  ${({ hexColor = '#bbb', outline = false }) => {
    const modifiedColor = modifyColorLightness(hexColor, isLightColor(hexColor) ? -1.3 : 1.3);

    return outline
      ? css`
          color: ${hexColor};
          border-color: ${hexColor};
        `
      : css`
          color: ${modifiedColor};
          border-color: ${hexColor};
          background-color: ${hexColor};
        `;
  }}
`;

const Tag: React.FC<TagProps> = ({ children, hexColor, outline }) => (
  <Span hexColor={hexColor} outline={outline}>
    {children}
  </Span>
);

export default Tag;
