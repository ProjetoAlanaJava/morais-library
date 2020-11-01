import React from 'react';

import ListItem from '../../components/ListItem';
import PageBody from '../../components/PageBody';

import avatar from '../../assets/images/avatar.png';

import './styles.css'

function UsersList(){
  
  return (
    <PageBody 
      title="Usuários - Lista"
      link="/usuarios/form"
      isForm={false}
    >
      <ListItem 
        key={1}
        avatar={avatar}
        titulo="Igor Felipe Sales" 
        description="Curso: Sistemas de Informação"
        editLink="/document/form"
        deleteLink="/document"
      />

    </PageBody>
  )
}

export default UsersList;