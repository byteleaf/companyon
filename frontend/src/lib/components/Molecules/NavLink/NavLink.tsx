import React from 'react';
import styled from 'styled-components';
import tw from 'twin.macro';
import { NavLink as Link } from 'react-router-dom';
import Icon, { StyledIcon } from '../../Atoms/Icons/Icon';
import Icons from '../../Atoms/Icons/components';

interface NavLinkProps {
  href: string;
  icon: keyof typeof Icons;
}

const A = styled(Link)`
  ${tw`mt-1 text-base flex items-center px-2 py-2 font-medium rounded-md cursor-pointer focus:outline-none focus:bg-gray-700 transition ease-in-out duration-150`};

  &.active {
    ${tw`leading-5 text-white bg-gray-900`}

    ${StyledIcon} svg {
      ${tw`text-white`}
    }
  }
  &:not(.active) {
    ${tw`leading-6 text-gray-300 hover:text-white hover:bg-gray-700 focus:text-white`}
  }

  ${StyledIcon} {
    ${tw`mr-4`};

    svg {
      ${tw`group-hover:text-white group-focus:text-white transition ease-in-out duration-150`}
    }
  }
`;

const NavLink: React.FC<NavLinkProps> = ({ children, href, icon }) => (
  <A to={href} className="group" activeClassName="active">
    <Icon icon={icon} />
    {children}
  </A>
);

export default NavLink;
