import { call, put } from 'redux-saga/effects';

import { loadUsers } from './actions';

import api from '../../../services/api';

export function* loadUsersSaga(){
  try{
    const companies = yield call( api.get, 'auth/usuarios/');
    
    yield put(loadUsers(companies.data))
  }catch(err) {
    yield put(loadUsers([]))
  }
}