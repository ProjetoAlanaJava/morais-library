import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import ListItem from '../../components/ListItem';
import PageBody from '../../components/PageBody';

import { ApplicationState } from '../../store';
import { loadUsersRequest } from '../../store/modules/users/actions';

import { User } from '../../utils/@types/Users';

import avatar from '../../assets/images/avatar.png';

import './styles.css'


function UsersList(){

  const dispatch = useDispatch()

  const { users } = useSelector( (state: ApplicationState) => state);

  useEffect(() => {
    dispatch(loadUsersRequest())
}, [dispatch]);


  return (
    <PageBody 
      title="Usuários - Lista"
      link="/usuarios/form"
      isForm={false}
    >
      
      { users.data.map( (user : User) => {
        console.log(user)
        
        return(
          <ListItem 
            key={user.id}
            avatar={avatar}
            user={user} 
            editLink="/users/form"
            deleteLink="/users"
          />
        )}
      )}

      

    </PageBody>
  )
}

export default UsersList;