import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import PageBody from '../../components/PageBody';
import ListItem from '../../components/ListItem';

import bookIcon from '../../assets/images/book.png';

import { ApplicationState } from '../../store';
import { loadBooksReservationsRequest } from '../../store/modules/books/actions';
import { BookReservation } from '../../store/modules/books/types';

import './styles.css';

function BooksReservationsList(){

  const dispatch = useDispatch()

  const { books } = useSelector( (state: ApplicationState) => state);

  useEffect(() => {
    dispatch(loadBooksReservationsRequest())
  }, [dispatch]);
  

  return (
    <PageBody 
      title="Minhas Reservas - Livros"
      link="/books"
      isForm={true}
    >
            
      { books.dataReservations.map( (book: BookReservation) => {
        return <div className="book-list">
        <ListItem 
            key={book.id}
            type="book"
            isBook={true}
            avatar={bookIcon}
            header={book.livro.titulo}
            description_one_title="Editora"
            description_one_value={book.livro.editora.nome}
            description_two_title="ISBN"
            description_two_value={book.livro.isbn}
            additional_information_title="Quantidade"
            additional_information_value={book.livro.qtd_geral}
            editLink="/users/form"
            deleteLink="/users"
            reserveLink='books'
        />
      </div>
      })}


    </PageBody>
  )
}

export default BooksReservationsList;