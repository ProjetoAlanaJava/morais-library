import { all, takeLatest} from 'redux-saga/effects'

import { UsersTypes } from './users/types';
import { loadUsersSaga } from './users/saga';
import { BooksTypes } from './books/types';
import { loadBooksSaga } from './books/saga';
import { EventsTypes } from './events/types';
import { loadEventsSaga } from './events/saga';


export default function* rootSaga(){
  return yield all([
    takeLatest(UsersTypes.LOAD_USERS_REQUEST, loadUsersSaga ),
    takeLatest(BooksTypes.LOAD_BOOKS_REQUEST, loadBooksSaga ),
    takeLatest(EventsTypes.LOAD_EVENTS_REQUEST, loadEventsSaga ),
  ])
} 