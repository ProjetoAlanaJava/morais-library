import { Reducer } from 'redux';
import {FichaCatalograficaState, FichaCatalograficaTypes  } from './types';

export interface UsersApplicationState{
  BOOKS: FichaCatalograficaState,
}

const INITIAL_STATE: FichaCatalograficaState = {
  data: [],
  isEdit: false,
  loading: false,
  formData: undefined,
  error: false
}


const reducer: Reducer<FichaCatalograficaState> = (state = INITIAL_STATE, action) : FichaCatalograficaState =>{
  
  switch(action.type){

    case FichaCatalograficaTypes.LOAD_FICHA_CATALOGRAFICAS_REQUEST:
      console.log('REDUCER - LOAD_FICHA_REQUEST')
      return {...state, loading: true, isEdit: false}

    case FichaCatalograficaTypes.LOAD_FICHA_CATALOGRAFICAS:
      console.log('REDUCER - LOAD_FICHA')
      return { ...state,  data: action.payload.data, loading: false, error: false,  isEdit: false}; 

    case FichaCatalograficaTypes.UPDATE_FICHA_CATALOGRAFICA:
      const fichaUpdate = action.payload.data;
      console.log('UPDATE_EVENT ',fichaUpdate)
      return { ...state, isEdit: false, formData: undefined}

    default:
      return state
  }
}

export default reducer;