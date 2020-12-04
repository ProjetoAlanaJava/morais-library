import { action } from 'typesafe-actions';
import { User, UsersTypes} from "./types";


export const setUser = (data: (User | undefined)) => action(UsersTypes.SET_USER,  { data });
export const getUser = () => action(UsersTypes.GET_USER );
export const resetUser = () => action(UsersTypes.RESET_USER );
export const updatePathUser = (newPath: string) => action(UsersTypes.UPDATE_PATH_USER, { newPath});