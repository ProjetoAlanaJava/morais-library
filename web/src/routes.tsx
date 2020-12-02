import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import Home from './Pages/Home';
import UsersList from './Pages/UsersList';
import BooksList from './Pages/BooksList';
import Login from './Pages/Login';
import EventList from './Pages/EventsList';

import { isAuthenticated } from './services/auth';

const PrivateRoute = ({...rest}) => {
    return isAuthenticated() ? <Route {...rest}/> : <Redirect to="/login"/>
}

function Routes(){
  return (
    <Switch>
      <Route path="/" exact component={Home}/>
      <Route path="/login" component={Login}/>
      <PrivateRoute path="/books" exact component={ BooksList}/>
      <PrivateRoute  path="/users" exact component={ UsersList }/>
      <PrivateRoute  path="/events" exact component={ EventList }/>
      <Route  path="*" component={() => <h1>Page not found</h1>} />
    </Switch>
  )
}

export default Routes