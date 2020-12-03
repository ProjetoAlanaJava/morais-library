import { Reducer } from 'redux';
import {EventsState, EventsTypes  } from './types';

export interface UsersApplicationState{
  BOOKS: EventsState,
}

const INITIAL_STATE: EventsState = {
  data: [],
  isEdit: false,
  loading: false,
  formData: undefined,
  error: false
}


const reducer: Reducer<EventsState> = (state = INITIAL_STATE, action) : EventsState =>{
  
  switch(action.type){

    case EventsTypes.LOAD_EVENTS_REQUEST:
      console.log('REDUCER - LOAD_EVENTS_REQUEST')
      return {...state, loading: true, isEdit: false}

    case EventsTypes.LOAD_EVENTS:
      console.log('REDUCER - LOAD_EVENTS')
      return { ...state,  data: action.payload.data, loading: false, error: false,  isEdit: false};

    

    case EventsTypes.SHOW_EVENT:
      var eventId = action.payload.data.id;
      console.log('REDUCER - SHOW_EVENT')
      const event = state.data.filter( (event) => {
        if(eventId === event.id){
          return event;
        }
        return null
      })

    return { ...state, isEdit: true,  data: event, formData: event[0]}

    case EventsTypes.UPDATE_EVENT:
      const eventUpdate = action.payload.data;
      console.log('UPDATE_EVENT ',eventUpdate)
      return { ...state, isEdit: false, formData: undefined}

    case EventsTypes.DELETE_EVENT:
      var eventDeleteId = action.payload.id;
      console.log('DELETE_EVENT')
      console.log(eventDeleteId)
      return {...state, data: state.data.filter( event => 
        event.id !== eventDeleteId
      )};

    default:
      return state
  }
}

export default reducer;