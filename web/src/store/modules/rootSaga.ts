import { all, takeLatest} from 'redux-saga/effects'

import { UsersTypes } from './users/types';
import { loadUsersSaga } from './users/saga';
import { BooksTypes } from './books/types';
import {  loadBooksReservationsSaga, loadBooksSaga } from './books/saga';
import { EventsTypes } from './events/types';
import { loadEventsSaga } from './events/saga';
import { SpacesTypes } from './spaces/types';
import { loadSpacesSaga } from './spaces/saga';
import { FichasTypes } from './fichaCatalografica/types';
import { loadFichasSaga } from './fichaCatalografica/saga';
// import { FichaCatalograficaTypes} from './fichaCatalografica/types';
// import { loadFichasSaga } from './fichaCatalografica/saga';


export default function* rootSaga(){
  return yield all([
    takeLatest(UsersTypes.LOAD_USERS_REQUEST, loadUsersSaga ),
    takeLatest(BooksTypes.LOAD_BOOKS_REQUEST, loadBooksSaga ),
    takeLatest(BooksTypes.LOAD_BOOKS_RESERVATIONS_REQUEST, loadBooksReservationsSaga ),
    takeLatest(EventsTypes.LOAD_EVENTS_REQUEST, loadEventsSaga ),
    takeLatest(SpacesTypes.LOAD_SPACES_REQUEST, loadSpacesSaga ),
    takeLatest(FichasTypes.LOAD_FICHAS_REQUEST, loadFichasSaga)
  ])
} 