import { Reducer } from 'redux';
import {SpacesState, SpacesTypes  } from './types';

export interface UsersApplicationState{
  SPACES: SpacesState,
}

const INITIAL_STATE: SpacesState = {
  data: [],
  isEdit: false,
  loading: false,
  error: false
}


const reducer: Reducer<SpacesState> = (state = INITIAL_STATE, action) : SpacesState =>{
  
  switch(action.type){

    case SpacesTypes.LOAD_SPACES_REQUEST:
      console.log('REDUCER - LOAD_SPACES_REQUEST')
      return {...state, loading: true, isEdit: false}

    case SpacesTypes.LOAD_SPACES:
      console.log('REDUCER - LOAD_SPACES')
      return { ...state,  data: action.payload.data, loading: false, error: false,  isEdit: false};

    default:
      return state
  }
}

export default reducer;