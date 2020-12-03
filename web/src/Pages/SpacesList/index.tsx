import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import PageBody from '../../components/PageBody';
import ListItem from '../../components/ListItem';

import bookIcon from '../../assets/images/book.png';

import { ApplicationState } from '../../store';
import { Space } from '../../store/modules/spaces/types';

import './styles.css';
import { loadSpacesRequest } from '../../store/modules/spaces/actions';

function SpacesList(){

  const dispatch = useDispatch()

  const { spaces } = useSelector( (state: ApplicationState) => state);

  useEffect(() => {
    dispatch(loadSpacesRequest())
  }, [dispatch]);
  

  return (
    <PageBody 
      title="Espaços - Lista"
      link="/spaces/form"
      isForm={false}
    >
            
      { spaces.data.map( (space: Space) => {
        console.log(space)
        return       <div className="book-list">
        <ListItem 
            key={space.id}
            type="space"
            space={space}
            avatar={bookIcon}
            header={space.nome}
            description_one_title="Setor"
            description_one_value={space.setor}
            description_two_title="Horario de abertura"
            description_two_value={space.horarioAbertura}
            additional_information_title="Capacidade"
            additional_information_value={space.capacidade}
            editLink="/spaces/form"
            deleteLink="/spaces"
        />
      </div>
      })}


    </PageBody>
  )
}

export default SpacesList;