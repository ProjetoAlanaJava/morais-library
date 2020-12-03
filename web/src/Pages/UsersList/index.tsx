import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import ListItem from '../../components/ListItem';
import PageBody from '../../components/PageBody';

import { ApplicationState } from '../../store';
import { loadUsersRequest } from '../../store/modules/users/actions';

import { User } from '../../store/modules/users/types';

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
      link="/users/form"
      isForm={false}
    >
      
      { console.log(users)}
      { users.data.map( (user : User) => {
        return(
          <ListItem 
            key={user.id}
            type="user"
            user={user}
            avatar={avatar}
            header={user.nome}
            description_one_title="Curso"
            description_one_value={user.curso.nome}
            description_two_title="Email"
            description_two_value={user.email}
            additional_information_title="Telefone"
            additional_information_value={user.telefone}
            editLink="/users/form"
            deleteLink="/users"
          />
        )}
      )}

      

    </PageBody>
  )
}

export default UsersList;