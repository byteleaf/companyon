import tw from 'twin.macro';
import styled, { css } from 'styled-components';

export default styled.button<{ disabled?: boolean }>`
  ${tw`text-indigo-600 hover:text-indigo-900 cursor-pointer focus:outline-none`}

  ${({ disabled }) =>
    disabled &&
    css`
      ${tw`opacity-50 pointer-events-none`}
    `}
`;
