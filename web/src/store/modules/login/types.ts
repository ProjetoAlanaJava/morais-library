export enum UsersTypes {
  GET_USER = 'USER/GET_USER',
  SET_USER = 'USER/SET_USER',
  RESET_USER = 'USER/RESET_USER',
  UPDATE_PATH_USER = 'USER/UPDATE_PATH_USER',
}

export interface User {
  token: string,
  usuario: {
    id: number,
    matricula: string,
    authority: string,
    nome: string,
    cpf: number,
    ativo: boolean,
    limiteLivros: number,
    telefone: string,
    email: string,
  }
};

export interface LoginState {
  readonly data: User | undefined;
  readonly loading: boolean;
  readonly error: boolean;
}