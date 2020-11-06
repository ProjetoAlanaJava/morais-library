import { Reducer } from 'redux';
import { UsersState, UsersTypes  } from './types';

export interface UsersApplicationState{
  USERS: UsersState,
}

const INITIAL_STATE: UsersState = {
  data: [],
  isEdit: false,
  loading: false,
  error: false
}


const reducer: Reducer<UsersState> = (state = INITIAL_STATE, action) : UsersState =>{
  
  switch(action.type){

    case UsersTypes.LOAD_USERS_REQUEST:
      console.log('REDUCER - LOAD_USERS_REQUEST')
      return {...state, loading: true, isEdit: false}

    case UsersTypes.LOAD_USERS:
      console.log('REDUCER - LOAD_USERS')
      return { ...state,  data: action.payload.data, loading: false, error: false,  isEdit: false};

    default:
      return state
  }
}

export default reducer;