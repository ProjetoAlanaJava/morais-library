import { Reducer } from 'redux';
import {SpacesState, SpacesTypes  } from './types';

export interface UsersApplicationState{
  SPACES: SpacesState,
}

const INITIAL_STATE: SpacesState = {
  data: [],
  isEdit: false,
  loading: false,
  formData: undefined,
  error: false
}


const reducer: Reducer<SpacesState> = (state = INITIAL_STATE, action) : SpacesState =>{
  
  switch(action.type){

    case SpacesTypes.ADD_SPACE:
      console.log('REDUCER - ADD_SPACE', action.payload.data)
      return { ...state};


    case SpacesTypes.LOAD_SPACES_REQUEST:
      console.log('REDUCER - LOAD_SPACES_REQUEST')
      return {...state, loading: true, isEdit: false, formData: undefined}

    case SpacesTypes.LOAD_SPACES:
      console.log('REDUCER - LOAD_SPACES')
      return { ...state,  data: action.payload.data, 
        loading: false, error: false,  isEdit: false, formData: undefined};


    case SpacesTypes.SHOW_SPACE:
      var spaceId = action.payload.data.id;
      console.log('REDUCER - SHOW_SPACE')
      const space = state.data.filter( (space) => {
        if(spaceId === space.id){
          return space;
        }
        return null
      })

    return { ...state, isEdit: true,  data: space, formData: space[0]}

    case SpacesTypes.UPDATE_SPACE:
      const spaceUpdate = action.payload.data;
      console.log('UPDATE_SPACE ',spaceUpdate)
      return { ...state, isEdit: false, formData: undefined}

    case SpacesTypes.DELETE_SPACE:
      var spaceDeleteId = action.payload.id;
      console.log('DELETE_SPACE')
      console.log(spaceDeleteId)
      return {...state, data: state.data.filter( space => 
        space.id !== spaceDeleteId
      )};
    
    default:
      return state
  }
}

export default reducer;