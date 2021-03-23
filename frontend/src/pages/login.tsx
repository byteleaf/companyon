import React, { useEffect } from 'react';

import { useHistory } from 'react-router-dom';
import styled from 'styled-components';
import tw from 'twin.macro';
import Button from '../lib/components/Atoms/Button/Button';

import { useAuthDispatch, useAuthState } from '../context/AuthContext';
import { useCurrentUserLazyQuery } from '../graphql/queries/__generated__/currentUser';

const S = {
  Wrapper: styled.div`
    ${tw`w-screen h-screen flex justify-center items-center flex-col`}
  `,
  Box: styled.div`
    ${tw`flex flex-col justify-center items-center bg-gray-50 m-8 p-8 rounded`}
  `,
  Title: styled.h1``,
  Error: styled.div`
    ${tw`mt-8 bg-red-500 p-1 rounded text-white`}
  `,
};

const Login = () => {
  const { push } = useHistory();

  const { authenticated, loading, hasToken } = useAuthState();
  const { login, logout } = useAuthDispatch();

  const [getUser, { data, error }] = useCurrentUserLazyQuery();

  useEffect(() => {
    if (authenticated && !loading && hasToken) {
      getUser();
    }
  }, [authenticated, getUser, loading, hasToken]);

  useEffect(() => {
    if (!error) return;
    logout();
  }, [error, logout]);

  useEffect(() => {
    if (loading || error || !data?.currentUser.id) return;
    push('/dashboard');
  }, [loading, error, data, push, authenticated]);

  return (
    <S.Wrapper>
      <S.Title>COMPANYON</S.Title>
      <S.Box>
        <Button onClick={() => login()}>Login</Button>
        {error && <S.Error>Error logging in</S.Error>}
      </S.Box>
    </S.Wrapper>
  );
};

export default Login;
