export enum FichasTypes {
  ADD_FICHA = 'FICHA/ADD_FICHA',
  SHOW_FICHA = 'FICHA/SHOW_FICHA',
  LOAD_FICHAS = 'FICHA/LOAD_FICHAS',
  LOAD_FICHAS_REQUEST = 'FICHA/LOAD_FICHAS_REQUEST',
  UPDATE_FICHA = 'FICHA/UPDATE_FICHA',
  DELETE_FICHA = 'FICHA/DELETE_FICHA',
}

export interface Autor {
  id:number
}
export interface Editora {
  id:number
}

export interface FichasCatalograficas {
  id:number,
  titulo : string,
  subtitulo : string,
  autor: Autor,
  assuntos: string,
  isbn : number,
  editora: Editora,
  localPublicacao : string,
  dataPublicacao : string,
  numPaginas : number,
  idEditora: number,
  status: string,
}


export interface FichasState {
  readonly data: FichasCatalograficas[];
  readonly isEdit: boolean;
  readonly formData: FichasCatalograficas | undefined;
  readonly loading: boolean;
  readonly error: boolean;
}