import { action } from 'typesafe-actions';
import { User, UsersTypes } from './types';


export const addUser = (data: User) => action(UsersTypes.ADD_USER,  { data });
export const loadUsersRequest = () => action( UsersTypes.LOAD_USERS_REQUEST);
export const loadUsers = (data: User[]) => action( UsersTypes.LOAD_USERS, { data } );
export const showUser = ( data: User) => action(UsersTypes.SHOW_USER, { data });
export const updateUser = ( data: User ) => action(UsersTypes.UPDATE_USER, { data } );
export const deleteUser = (id: number) => action(UsersTypes.DELETE_USER, { id });