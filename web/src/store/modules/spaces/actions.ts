import { action } from 'typesafe-actions';
import { Space, SpacesTypes } from './types';

export const loadSpacesRequest = () => action( SpacesTypes.LOAD_SPACES_REQUEST);
export const loadSpaces = (data: Space []) => action( SpacesTypes.LOAD_SPACES, { data } );
