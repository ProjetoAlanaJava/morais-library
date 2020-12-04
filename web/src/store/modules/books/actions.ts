import { action } from 'typesafe-actions';
import { Book, BookReservation, BooksTypes } from './types';

export const loadBooksRequest = () => action( BooksTypes.LOAD_BOOKS_REQUEST);
export const loadBooks = (data: Book []) => action( BooksTypes.LOAD_BOOKS, { data } );
export const loadBooksReservationsRequest = () => action( BooksTypes.LOAD_BOOKS_RESERVATIONS_REQUEST);
export const loadBooksReservations = (data: BookReservation []) => action( BooksTypes.LOAD_BOOKS_RESERVATIONS, { data } );
