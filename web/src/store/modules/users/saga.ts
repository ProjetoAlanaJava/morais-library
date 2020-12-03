import { call, put } from 'redux-saga/effects';

import { loadUsers } from './actions';

import api from '../../../services/api';

export function* loadUsersSaga(){
  try{
    const users = yield call( api.get, 'auth/usuarios/');
    console.log('USER SAGA - ', users)
    yield put(loadUsers(users.data))
  }catch(err) {
    yield put(loadUsers([]))
  }
}