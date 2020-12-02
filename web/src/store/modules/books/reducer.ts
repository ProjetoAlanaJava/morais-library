import { Reducer } from 'redux';
import {BooksState, BooksTypes  } from './types';

export interface UsersApplicationState{
  BOOKS: BooksState,
}

const INITIAL_STATE: BooksState = {
  data: [],
  isEdit: false,
  loading: false,
  error: false
}


const reducer: Reducer<BooksState> = (state = INITIAL_STATE, action) : BooksState =>{
  
  switch(action.type){

    case BooksTypes.LOAD_BOOKS_REQUEST:
      console.log('REDUCER - LOAD_BOOKS_REQUEST')
      return {...state, loading: true, isEdit: false}

    case BooksTypes.LOAD_BOOKS:
      console.log('REDUCER - LOAD_BOOKS')
      return { ...state,  data: action.payload.data, loading: false, error: false,  isEdit: false};

    default:
      return state
  }
}

export default reducer;