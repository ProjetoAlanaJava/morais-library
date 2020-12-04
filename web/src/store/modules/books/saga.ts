import { call, put } from 'redux-saga/effects';

import { loadBooks, loadBooksReservations } from './actions';

import api from '../../../services/api';
import store from '../..';

export function* loadBooksSaga(){
  try{
    const books = yield call( api.get, 'livros/');
    
    yield put(loadBooks(books.data))
  }catch(err) {
    yield put(loadBooks([]))
  }
}
export function* loadBooksReservationsSaga(){
  try{
    console.log('RESERVATIONS - BOOK - SAGA')
    const id = store.getState().login.data?.usuario.id
    const booksReservations = yield call( api.get, `reserva-livro/${id}`);
    yield put(loadBooksReservations(booksReservations.data))
  }catch(err) {
    yield put(loadBooksReservations([]))
  }
}