import { Reducer } from 'redux';
import { UsersState, UsersTypes  } from './types';

export interface UsersApplicationState{
  USERS: UsersState,
}

const INITIAL_STATE: UsersState = {
  data: [],
  isEdit: false,
  formData: undefined,
  loading: false,
  error: false
}


const reducer: Reducer<UsersState> = (state = INITIAL_STATE, action) : UsersState =>{
  
  switch(action.type){

    case UsersTypes.ADD_USER:
      console.log('REDUCER - ADD_USER', action.payload.data)
      return { ...state};

    case UsersTypes.LOAD_USERS_REQUEST:
      console.log('REDUCER - LOAD_USERS_REQUEST')
      return {...state, loading: true, isEdit: false}

    case UsersTypes.LOAD_USERS:
      console.log('REDUCER - LOAD_USERS')
      return { ...state,  data: action.payload.data, loading: false, error: false,  isEdit: false};

    
    case UsersTypes.SHOW_USER:
      var userId = action.payload.data.id;

      const user = state.data.filter( (user) => {
        if(userId === user.id){
          return user;
        }
        return null
      })

      return { ...state, isEdit: true,  data: user, formData: user[0]}

    case UsersTypes.UPDATE_USER:
      const userUpdate = action.payload.data;
      console.log('UPDATE_USER ',userUpdate)
      return { ...state, isEdit: false, formData: undefined}

    case UsersTypes.DELETE_USER:
      var userDeleteId = action.payload.id;
      console.log('DELETE_USER')
      console.log(userDeleteId)
      return {...state, data: state.data.filter( user => 
        user !== userDeleteId
      )};

    default:
      return state
  }
}

export default reducer;