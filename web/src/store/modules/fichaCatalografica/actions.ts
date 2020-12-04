import { action } from 'typesafe-actions';
import { FichasCatalograficas, FichasTypes } from './types';

// export const addEvent = (data: Event) => action(FichasTypes.ADD_FICHA,  { data });
export const loadFichasRequest = () => action( FichasTypes.LOAD_FICHAS_REQUEST);
export const loadFichas = (data: FichasCatalograficas []) => action( FichasTypes.LOAD_FICHAS, { data } );
// export const showEvent = ( data: Event) => action(FichasTypes.SHOW_EVENT, { data });
// export const updateEvent = ( data: Event ) => action(FichasTypes.UPDATE_EVENT, { data } );
// export const deleteEvent = (id: number) => action(FichasTypes.DELETE_EVENT, { id });
