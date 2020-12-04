export enum BooksTypes {
  ADD_BOOK = 'BOOK/ADD_BOOK',
  SHOW_BOOK = 'BOOK/SHOW_BOOK',
  LOAD_BOOKS = 'BOOK/LOAD_BOOKS',
  LOAD_BOOKS_REQUEST = 'BOOK/LOAD_BOOKS_REQUEST',
  UPDATE_BOOK = 'BOOK/UPDATE_BOOK',
  DELETE_BOOK = 'BOOK/DELETE_BOOK',
  ADD_BOOK_RESERVATIONS = 'BOOK/ADD_BOOK_RESERVATIONS',
  LOAD_BOOKS_RESERVATIONS = 'BOOK/LOAD_BOOKS_RESERVATIONS',
  LOAD_BOOKS_RESERVATIONS_REQUEST = 'BOOK/LOAD_BOOKS__RESERVATIONS_REQUEST',
}

export interface BookReservation{
  "id": number,
  "livro": Book;
  "dataMaximaEmprestimo": string;
}

export interface Book {
  id: number;
  titulo: string;
  autores: Autor [];
  assunto: string;
  qtd_geral: number;
  ano_publicacao: number;
  editora: Editora;
  tipo: Tipo;
  categoria: Categoria;
  isbn: string; 
}


export interface Autor {
  id: number;
  name: string;
  bio: string;
  pais: string;
}

export interface Editora {
  id: number;
  nome: string;
  cnpj: string;
  telefone: string;
  email: string;
  pais: string;
}

export interface Categoria {
  id: number;
  nome: string;
  descricao: string;
}

export interface Tipo {
  id: number;
  nome: string;
}

export interface BooksState {
  readonly data: Book[];
  readonly dataReservations: BookReservation[];
  readonly isEdit: boolean;
  // readonly formData: Book | undefined;
  readonly loading: boolean;
  readonly error: boolean;
}