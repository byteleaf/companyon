import { useCurrentUserQuery } from '../graphql/queries/__generated__/currentUser';

export const useUser = () => {
  const { data, loading } = useCurrentUserQuery();

  return {
    user: data?.currentUser,
    loading,
    isAdmin: data?.currentUser?.admin || false,
  };
};
