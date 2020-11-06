import { action } from 'typesafe-actions';
import { User, UsersTypes } from './types';

export const loadUsersRequest = () => action( UsersTypes.LOAD_USERS_REQUEST);
export const loadUsers = (data: User[]) => action( UsersTypes.LOAD_USERS, { data } );
