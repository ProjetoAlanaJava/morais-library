import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import Home from './Pages/Home';
import UsersList from './Pages/UsersList';
import BooksList from './Pages/BooksList';
import Login from './Pages/Login';
import EventList from './Pages/EventsList';
import SpacesList from './Pages/SpacesList';
import UserForm from './Pages/UsersForm';
import SpacesForm from './Pages/SpacesForm';
import EventsForm from './Pages/EventsForm';
import SpacesReservationsForm from './Pages/ReservationsForms/SpacesReservations';
import BooksReservationsList from './Pages/BooksReservationsList';
import SolicitacaoFicha from './Pages/FichaCatalografica/SolicitacaoFicha';
import UploadsForm from './Pages/uploadForm';
import FichaList from './Pages/FichaCatalografica/FichaList';
import LoanForm from './Pages/LoanForm';

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
      <PrivateRoute path="/books/my-reservations" exact component={ BooksReservationsList}/>
      <PrivateRoute  path="/users" exact component={ UsersList }/>
      <PrivateRoute  path="/users/uploads" exact component={ UploadsForm }/>
      <PrivateRoute  path="/users/form" exact component={ UserForm}/>
      <PrivateRoute  path="/events" exact component={ EventList }/>
      <PrivateRoute  path="/events/form" exact component={ EventsForm }/>
      <PrivateRoute  path="/spaces" exact component={ SpacesList }/>
      <PrivateRoute  path="/spaces/form" exact component={ SpacesForm}/>
      <PrivateRoute  path="/spaces/reservation/form" exact component={ SpacesReservationsForm}/>
      <PrivateRoute  path="/ficha-catalografica" exact component={ FichaList}/>
      <PrivateRoute  path="/ficha-catalografica/solicitacao" exact component={ SolicitacaoFicha}/>
      <PrivateRoute  path="/loans/form" exact component={ LoanForm}/>
      <Route  path="*" component={() => <h1>Page not found</h1>} />
    </Switch>
  )
}

export default Routes