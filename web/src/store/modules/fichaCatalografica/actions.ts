import { action } from 'typesafe-actions';
import { FichaCatalografica, FichaCatalograficaTypes } from './types';

export const addFichaCatalografica = (data: FichaCatalografica) => action(FichaCatalograficaTypes.ADD_FICHA_CATALOGRAFICA,  { data });
export const loadFichaCatalograficasRequest = () => action( FichaCatalograficaTypes.LOAD_FICHA_CATALOGRAFICAS_REQUEST);
export const loadFichaCatalograficas = (data: FichaCatalografica []) => action( FichaCatalograficaTypes.LOAD_FICHA_CATALOGRAFICAS, { data } );
export const updateFichaCatalografica = ( data: FichaCatalografica ) => action(FichaCatalograficaTypes.UPDATE_FICHA_CATALOGRAFICA, { data } );
