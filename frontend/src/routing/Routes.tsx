import * as React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PrivateRoute from './PrivateRoute';
import Companies from '../pages/companies';
import Company from '../pages/companies/company';
import LogWork from '../pages/log-work';
import Profile from '../pages/profile';
import Projects from '../pages/projects';
import Project from '../pages/projects/project';
import Users from '../pages/users';
import Dashboard from '../pages/dashboard';
import Login from '../pages/login';
import User from '../pages/users/user';
import { Root } from '../pages/root';

const Routes = () => (
  <Router>
    <Switch>
      <Route path="/" exact component={Root} />
      <Route path="/login" component={Login} />
      <PrivateRoute path="/dashboard" component={Dashboard} />
      <PrivateRoute path="/companies" exact component={Companies} />
      <PrivateRoute path="/companies/:id" component={Company} />
      <PrivateRoute path="/log-work" component={LogWork} />
      <PrivateRoute path="/projects/:id" component={Project} />
      <PrivateRoute path="/projects" component={Projects} />
      <PrivateRoute path="/users/:id" component={User} />
      <PrivateRoute path="/users" component={Users} />
      <PrivateRoute path="/profile" component={Profile} />
    </Switch>
  </Router>
);

export default Routes;
