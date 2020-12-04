import { call, put } from 'redux-saga/effects';

import { loadFichas } from './actions';

import api from '../../../services/api';

export function* loadFichasSaga(){
  try{
    const fichas = yield call( api.get, 'ficha-catalografica/');
    
    yield put(loadFichas(fichas.data))
  }catch(err) {
    yield put(loadFichas([]))
  }
}