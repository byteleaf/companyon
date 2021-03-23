import React, { useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import { useAuthState } from '../context/AuthContext';

export const Root = () => {
  const { authenticated, loading } = useAuthState();
  const { push } = useHistory();

  useEffect(() => {
    if (loading) {
      return;
    }
    if (authenticated) {
      push('/dashboard');
    } else {
      push('/login');
    }
  }, [authenticated, loading, push]);

  return <div>Loading...</div>;
};
