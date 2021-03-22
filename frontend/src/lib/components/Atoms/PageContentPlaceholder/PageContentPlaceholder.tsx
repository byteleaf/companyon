import React from 'react';
import styled from 'styled-components';
import tw from 'twin.macro';

const Wrapper = styled.div`
  ${tw`py-4`};
`;

const Placeholder = styled.div`
  ${tw`border-4 border-dashed border-gray-200 rounded-lg h-96`};
`;

const PageContentPlaceholder = () => (
  <Wrapper>
    <Placeholder />
  </Wrapper>
);

export default PageContentPlaceholder;
