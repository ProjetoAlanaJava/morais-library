import { call, put } from 'redux-saga/effects';

import { loadSpaces } from './actions';

import api from '../../../services/api';

export function* loadSpacesSaga(){
  try{
    const books = yield call( api.get, 'espacos/');
    
    yield put(loadSpaces(books.data))
  }catch(err) {
    yield put(loadSpaces([]))
  }
}