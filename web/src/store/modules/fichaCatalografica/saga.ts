import { call, put } from 'redux-saga/effects';

import { loadFichaCatalograficas } from './actions';

import api from '../../../services/api';

export function* loadEventsSaga(){
  try{
    const books = yield call( api.get, 'ficha-catalografica/');
    
    yield put(loadFichaCatalograficas(books.data))
  }catch(err) {
    yield put(loadFichaCatalograficas([]))
  }
}