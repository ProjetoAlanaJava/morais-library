import { call, put } from 'redux-saga/effects';

import { loadBooks } from './actions';

import api from '../../../services/api';

export function* loadBooksSaga(){
  try{
    const books = yield call( api.get, 'livros/');
    
    yield put(loadBooks(books.data))
  }catch(err) {
    yield put(loadBooks([]))
  }
}