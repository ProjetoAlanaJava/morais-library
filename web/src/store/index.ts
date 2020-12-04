import { applyMiddleware, createStore, Store } from 'redux';
import createSagaMiddleware from 'redux-saga';

import { UsersState } from './modules/users/types';
import { BooksState } from './modules/books/types';
import { EventsState } from './modules/events/types';
import { SpacesState } from './modules/spaces/types';
import { LoginState } from './modules/login/types'
// import { FichaCatalograficaState } from './modules/fichaCatalografica/types';

import rootReducer from './modules/rootReducer';
import rootSaga from './modules/rootSaga';
import { FichasState } from './modules/fichaCatalografica/types';

export interface ApplicationState {
  users: UsersState,
  books: BooksState,
  events: EventsState,
  spaces: SpacesState,
  login: LoginState,
  fichaCatalografica: FichasState,
}

const sagaMiddleware = createSagaMiddleware();

const store: Store<ApplicationState> = createStore(rootReducer, applyMiddleware(sagaMiddleware)); 

sagaMiddleware.run(rootSaga)

export default store;
