import { action } from 'typesafe-actions';
import { Book, BooksTypes } from './types';

export const loadBooksRequest = () => action( BooksTypes.LOAD_BOOKS_REQUEST);
export const loadBooks = (data: Book []) => action( BooksTypes.LOAD_BOOKS, { data } );
