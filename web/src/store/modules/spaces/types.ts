export enum SpacesTypes {
  ADD_SPACE = 'SPACE/ADD_SPACE',
  SHOW_SPACE = 'SPACE/SHOW_SPACE',
  LOAD_SPACES = 'SPACE/LOAD_SPACES',
  LOAD_SPACES_REQUEST = 'SPACE/LOAD_SPACES_REQUEST',
  UPDATE_SPACE = 'SPACE/UPDATE_SPACE',
  DELETE_SPACE = 'SPACE/DELETE_SPACE',
}

export interface Space {
  id: number,
  nome: string,
  setor: string,
  horarioAbertura: string,
  horarioFechamento: string,
  tipo: string,
  capacidade: number,
  andar: number,
}
export interface SpaceReservation {
  data: string,
  horarioInicioReserva: string,
  horarioFimReserva: string,
}


export interface SpacesState {
  readonly data: Space [];
  readonly isEdit: boolean;
  readonly formData: Space | undefined;
  readonly loading: boolean;
  readonly error: boolean;
}