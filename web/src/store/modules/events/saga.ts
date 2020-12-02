import { call, put } from 'redux-saga/effects';

import { loadEvents } from './actions';

import api from '../../../services/api';

export function* loadEventsSaga(){
  try{
    const books = yield call( api.get, 'eventos/');
    
    yield put(loadEvents(books.data))
  }catch(err) {
    yield put(loadEvents([]))
  }
}