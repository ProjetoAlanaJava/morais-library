import { Reducer } from 'redux';
import {BooksState, BooksTypes  } from './types';

export interface UsersApplicationState{
  BOOKS: BooksState,
}

const INITIAL_STATE: BooksState = {
  data: [],
  dataReservations: [],
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

    case BooksTypes.ADD_BOOK_RESERVATIONS:
      console.log('REDUCER - ADD_BOOK_RESERVATIONS', action.payload.data)
      return { ...state};

    case BooksTypes.LOAD_BOOKS_RESERVATIONS_REQUEST:
      console.log('REDUCER - LOAD_BOOKS_RESERVATIONS_REQUEST')
      return {...state, loading: true, isEdit: false}

    case BooksTypes.LOAD_BOOKS_RESERVATIONS:
      console.log('REDUCER - LOAD_BOOKS_RESERVATIONS')
      return { ...state,  dataReservations: action.payload.data, loading: false, error: false,  isEdit: false};

    default:
      return state
  }
}

export default reducer;