import { all, takeLatest} from 'redux-saga/effects'

import { UsersTypes } from './users/types';
import { loadUsersSaga } from './users/saga';


export default function* rootSaga(){
  return yield all([
    takeLatest(UsersTypes.LOAD_USERS_REQUEST, loadUsersSaga ),
  ])
} 