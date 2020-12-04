import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import PageBody from '../../../components/PageBody';
import ListItem from '../../../components/ListItem';

import bookIcon from '../../../assets/images/book.png';

import { ApplicationState } from '../../../store';

import './styles.css';
import { loadFichasRequest } from '../../../store/modules/fichaCatalografica/actions';
import { FichasCatalograficas } from '../../../store/modules/fichaCatalografica/types';

function FichaList(){

  const dispatch = useDispatch()

  const { fichaCatalografica } = useSelector( (state: ApplicationState) => state);

  useEffect(() => {
    dispatch(loadFichasRequest())
  }, [dispatch]);
  

  return (
    <PageBody 
      title="Fichas Catalográficas - Lista"
      // link="/spaces/form"
      isForm={false}
      isReserve={true}
      // isBook={false}
    >
            
      { fichaCatalografica.data.map( (ficha: FichasCatalograficas) => {
        
        return <div className="book-list">
        <ListItem 
            key={ficha.isbn}
            fichaLink="/ficha-catalografica"
            isEdit={false}
            avatar={bookIcon}
            ficha={ficha}
            header={ficha.titulo}
            description_one_title="Subtítulo"
            description_one_value={ficha.subtitulo}
            description_two_title="Assuntos"
            description_two_value={ficha.assuntos}
            additional_information_title="Data de publicação"
            additional_information_value={ficha.dataPublicacao}
            editLink="/fichas/form"
            deleteLink="/fichas"
        />
      </div>
      })}


    </PageBody>
  )
}

export default FichaList;