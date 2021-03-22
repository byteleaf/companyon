import { Link } from 'react-router-dom';
import styled from 'styled-components';
import tw from 'twin.macro';

export default styled(Link)<{ inline?: boolean }>`
  ${tw`text-indigo-600 hover:text-indigo-900`}

  ${({ inline }) => inline && tw`underline hover:no-underline`}
`;
