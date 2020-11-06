import React from 'react';

import PageBody from '../../components/PageBody';
import ListItem from '../../components/ListItem';

import book from '../../assets/images/book.png';

import './styles.css';

function UsersList(){
  return (
    <PageBody 
      title="Livros - Lista"
      link="/livros/form"
      isForm={false}
    >
      <div className="book-list">
        <ListItem 
          key={1}
          avatar={book} 
          editLink="/books/form"
          deleteLink="/books"
        />
      </div>
    </PageBody>
  )
}

export default UsersList;