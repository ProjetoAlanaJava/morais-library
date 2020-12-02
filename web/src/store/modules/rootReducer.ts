import { combineReducers } from 'redux';

import users from './users/reducer';
import books from './books/reducer';
import events from './events/reducer';

export default combineReducers({
  users,
  books,
  events
});