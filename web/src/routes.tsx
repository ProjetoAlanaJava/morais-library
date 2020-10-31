import React from 'react';
import { Route, Switch } from 'react-router-dom';

import Home from './Pages/Home';
import Users from './Pages/UsersList';
import Books from './Pages/BooksList';

function Routes(){
  return (
    <Switch>
      <Route path="/" exact component={ Home }/>
      <Route path="/books" exact component={ Books}/>
      <Route path="/users" exact component={ Users }/>
      <Route path="*" component={() => <h1>Page not found</h1>} />
    </Switch>
  )
}

export default Routes