import React from 'react';
import styled from 'styled-components';
import tw from 'twin.macro';
import { useTranslation } from 'react-i18next';
import Avatar from '../../Atoms/Avatar/Avatar';
import LoadingSkeleton from '../../Atoms/LoadingSkeleton/LoadingSkeleton';
import { GqlFullUserFragment } from '../../../../graphql/fragments/__generated__/fullUser';
import { useAuthDispatch, useAuthState } from '../../../../context/AuthContext';
import Link from '../../Atoms/Link/Link';

interface ProfileLinkProps {
  loading: boolean;
  currentUser?: GqlFullUserFragment;
}

const Wrapper = styled.div`
  ${tw`flex-shrink-0 flex bg-gray-700 p-4`};
`;

const Flex = styled.div`
  ${tw`flex items-center`}
`;

const Caption = styled.div`
  ${tw`ml-3`}
`;

const UserName = styled.p`
  ${tw`text-base leading-6 font-medium text-white`}
`;

const Button = styled.button`
  ${tw`focus:outline-none text-sm leading-5 font-medium text-gray-400 group-hover:text-gray-300 transition ease-in-out duration-150`};
`;

const SmallLoadingSkeleton = styled(LoadingSkeleton)`
  ${tw`w-12 mr-1`};
`;

const LargeLoadingSkeleton = styled(LoadingSkeleton)`
  ${tw`w-16`};
`;

const ProfileLink: React.FC<ProfileLinkProps> = ({ loading, currentUser }) => {
  const { authenticated } = useAuthState();
  const { logout } = useAuthDispatch();
  const { t } = useTranslation();

  return (
    <Wrapper>
      <Flex>
        <Link to="/profile">
          <Avatar imgSrc={currentUser?.avatar?.url} alt={t('components.molecules.profileLink.avatar.alt')} />
        </Link>
        <Caption>
          {!loading && (
            <Link to="/profile">
              <UserName>{`${currentUser?.firstName} ${currentUser?.lastName}`}</UserName>
            </Link>
          )}
          {loading && (
            <UserName>
              <SmallLoadingSkeleton inline />
              <LargeLoadingSkeleton inline />
            </UserName>
          )}
          {authenticated && (
            <Button type="button" onClick={logout}>
              {t('components.molecules.profileLink.logout')}
            </Button>
          )}
        </Caption>
      </Flex>
    </Wrapper>
  );
};

export default ProfileLink;
