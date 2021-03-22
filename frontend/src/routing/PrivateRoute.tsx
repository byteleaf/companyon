/* eslint-disable react/jsx-curly-newline */
import React from 'react';
import { Route, Redirect, RouteComponentProps } from 'react-router-dom';
import type { RouteProps } from 'react-router-dom';
import { useAuthState } from '../context/AuthContext';

interface PrivateRouteParams extends RouteProps {
  component: React.ComponentType<RouteComponentProps> | React.ComponentType;
}

export default function PrivateRoute({ component: Component, ...rest }: PrivateRouteParams) {
  const { authenticated } = useAuthState();

  return (
    <Route
      {...rest}
      render={(props) =>
        authenticated ? (
          <Component {...props} />
        ) : (
          <Redirect
            to={{
              pathname: '/login',
              state: { from: props.location },
            }}
          />
        )
      }
    />
  );
}
