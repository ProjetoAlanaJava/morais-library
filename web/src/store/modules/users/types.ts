export enum UsersTypes {
  ADD_USER = 'USER/ADD_USER',
  SHOW_USER = 'USER/SHOW_USER',
  LOAD_USERS = 'USER/LOAD_USER',
  LOAD_USERS_REQUEST = 'USER/LOAD_USER_REQUEST',
  UPDATE_USER = 'USER/UPDATE_USER',
  DELETE_USER = 'USER/DELETE_USER',
}


export interface User{
  matricula: string,
  id: number;
  nome: string;
  cpf: number;
  ativo: boolean;
  curso: Curso;
  authority: number
  cargo: string,
  cursoId: number,
  // departamento: Departamento;
  tipo: string;
  // limiteLivros: number;
  telefone?: string;
  email: string;
}


export interface Curso {
  id: number;
  nome: string;
  area: string;
  tipo: string;
}

export interface Departamento {
  id: number;
  nome: string;
  area: string;
}

export interface UsersState {
  readonly data: User[];
  readonly isEdit: boolean;
  readonly formData: User | undefined;
  readonly loading: boolean;
  readonly error: boolean;
}