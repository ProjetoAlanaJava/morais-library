import { Reducer } from 'redux';
import {EventsState, EventsTypes  } from './types';

export interface UsersApplicationState{
  BOOKS: EventsState,
}

const INITIAL_STATE: EventsState = {
  data: [],
  isEdit: false,
  loading: false,
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

    default:
      return state
  }
}

export default reducer;