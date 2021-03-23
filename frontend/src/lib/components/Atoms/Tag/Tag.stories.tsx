import React from 'react';
import styled from 'styled-components';
import tw from 'twin.macro';
import Tag from './Tag';

export default {
  component: Tag,
  title: 'Atoms/Tag',
};

const Wrapper = styled.div`
  ${tw`max-w-xs`};

  span {
    ${tw`m-1`};
  }
`;

export const WithoutColorProp = () => <Tag>Tag Text</Tag>;

export const WithColorProp = () => (
  <Wrapper>
    <Tag hexColor="#1bcb19">Colored Tag</Tag>
    <Tag hexColor="#90a4fa">Colored Tag</Tag>
    <Tag hexColor="#5d5c96">Colored Tag</Tag>
    <Tag hexColor="#1b5b79">Colored Tag</Tag>
    <Tag hexColor="#633c79">Colored Tag</Tag>
    <Tag hexColor="#eb4034">Colored Tag</Tag>
    <Tag hexColor="#e8d905">Colored Tag</Tag>
    <Tag hexColor="#319e02">Colored Tag</Tag>
    <Tag hexColor="#00b588">Colored Tag</Tag>
  </Wrapper>
);

export const WithOutlineProp = () => (
  <Tag hexColor="#633c79" outline>
    Outline Tag
  </Tag>
);
