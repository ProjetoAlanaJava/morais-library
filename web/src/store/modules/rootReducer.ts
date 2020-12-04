import { combineReducers } from 'redux';

import users from './users/reducer';
import books from './books/reducer';
import events from './events/reducer';
import spaces from './spaces/reducer';
import login from './login/reducer';
import fichaCatalografica from './fichaCatalografica/reducer';

export default combineReducers({
  users,
  books,
  events,
  spaces,
  login,
  fichaCatalografica,
});