export enum FichaCatalograficaTypes {
  ADD_FICHA_CATALOGRAFICA = 'FICHA_CATALOGRAFICA/ADD_FICHA_CATALOGRAFICA',
  SHOW_FICHA_CATALOGRAFICA = 'FICHA_CATALOGRAFICA/SHOW_FICHA_CATALOGRAFICA',
  LOAD_FICHA_CATALOGRAFICAS = 'FICHA_CATALOGRAFICA/LOAD_FICHA_CATALOGRAFICAS',
  LOAD_FICHA_CATALOGRAFICAS_REQUEST = 'FICHA_CATALOGRAFICA/LOAD_FICHA_CATALOGRAFICAS_REQUEST',
  UPDATE_FICHA_CATALOGRAFICA = 'FICHA_CATALOGRAFICA/UPDATE_FICHA_CATALOGRAFICA',
  DELETE_FICHA_CATALOGRAFICA = 'FICHA_CATALOGRAFICA/DELETE_FICHA_CATALOGRAFICA',
}

export interface Autor {
  id: number
}

export interface Editora {
  id: number
}

export interface FichaCatalografica {
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
}


export interface FichaCatalograficaState {
  readonly data: FichaCatalografica[];
  readonly isEdit: boolean;
  readonly formData: FichaCatalografica | undefined;
  readonly loading: boolean;
  readonly error: boolean;
}