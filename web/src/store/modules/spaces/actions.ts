import { action } from 'typesafe-actions';
import { Space, SpacesTypes } from './types';

export const addSpace = (data: Space) => action(SpacesTypes.ADD_SPACE,  { data });
export const loadSpacesRequest = () => action( SpacesTypes.LOAD_SPACES_REQUEST);
export const loadSpaces = (data: Space []) => action( SpacesTypes.LOAD_SPACES, { data } );
export const showSpace = ( data: Space) => action(SpacesTypes.SHOW_SPACE, { data });
export const updateSpace = ( data: Space ) => action(SpacesTypes.UPDATE_SPACE, { data } );
export const deleteSpace = (id: number) => action(SpacesTypes.DELETE_SPACE, { id });