import { Reducer } from 'redux';
import { FichasState, FichasTypes  } from './types';

export interface FichasApplicationState{
  FICHAS: FichasState
}

const INITIAL_STATE: FichasState = {
  data: [],
  isEdit: false,
  loading: false,
  formData: undefined,
  error: false
}


const reducer: Reducer<FichasState> = (state = INITIAL_STATE, action) : FichasState =>{
  
  switch(action.type){

    case FichasTypes.LOAD_FICHAS_REQUEST:
      console.log('REDUCER - LOAD_FICHAS_REQUEST')
      return {...state, loading: true, isEdit: false}

    case FichasTypes.LOAD_FICHAS:
      console.log('REDUCER - LOAD_FICHAS')
      return { ...state,  data: action.payload.data, loading: false, error: false,  isEdit: false};

    

    // case EventsTypes.SHOW_EVENT:
    //   var eventId = action.payload.data.id;
    //   console.log('REDUCER - SHOW_EVENT')
    //   const event = state.data.filter( (event) => {
    //     if(eventId === event.id){
    //       return event;
    //     }
    //     return null
    //   })

    // return { ...state, isEdit: true,  data: event, formData: event[0]}

    // case EventsTypes.UPDATE_EVENT:
    //   const eventUpdate = action.payload.data;
    //   console.log('UPDATE_EVENT ',eventUpdate)
    //   return { ...state, isEdit: false, formData: undefined}

    // case EventsTypes.DELETE_EVENT:
    //   var eventDeleteId = action.payload.id;
    //   console.log('DELETE_EVENT')
    //   console.log(eventDeleteId)
    //   return {...state, data: state.data.filter( event => 
    //     event.id !== eventDeleteId
    //   )};

    default:
      return state
  }
}

export default reducer;