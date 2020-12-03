export enum EventsTypes {
  ADD_EVENT = 'EVENT/ADD_EVENT',
  SHOW_EVENT = 'EVENT/SHOW_EVENT',
  LOAD_EVENTS = 'EVENT/LOAD_EVENTS',
  LOAD_EVENTS_REQUEST = 'EVENT/LOAD_EVENTS_REQUEST',
  UPDATE_EVENT = 'EVENT/UPDATE_EVENT',
  DELETE_EVENT = 'EVENT/DELETE_EVENT',
}

export interface Event {
  id: number,
  title: string,
  date: Date,
  horarioInicio: string,
  horarioTermino: string,
  status: string,
}


export interface EventsState {
  readonly data: Event[];
  readonly isEdit: boolean;
  readonly formData: Event | undefined;
  readonly loading: boolean;
  readonly error: boolean;
}