import { Reducer } from 'redux';
import { LoginState, UsersTypes  } from './types';


const INITIAL_STATE: LoginState = {
  data: undefined,
  loading: false,
  error: false,
}


const reducer: Reducer<LoginState> = ( state = INITIAL_STATE, action): LoginState=> {
  switch(action.type){
    case UsersTypes.SET_USER:
      console.log('REDUCER - SET_USER')
      const { data } = action.payload
      return { ...state, data: data };
    case UsersTypes.GET_USER:
      console.log('REDUCER - GET_USER', state)
      return { ...state };  
    case UsersTypes.RESET_USER:
      console.log('REDUCER - RESET_USER')
      return { 
        ...state,
        data: undefined,
        error: false,
        loading:false,
      };
    case UsersTypes.UPDATE_PATH_USER:
      console.log('REDUCER - RESET_USER')
      return { ...state };
      
    default:
      return {...state, error: true}
  }
}


export default reducer;