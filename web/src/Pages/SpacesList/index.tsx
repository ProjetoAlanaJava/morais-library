import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import PageBody from '../../components/PageBody';
import ListItem from '../../components/ListItem';

import SpaceIcon from '../../assets/images/icons/space-icon.svg';

import { ApplicationState } from '../../store';
import { loadSpacesRequest } from '../../store/modules/spaces/actions';
import { Space } from '../../store/modules/spaces/types';

import './styles.css';

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
      isReserve={true}
      // isBook={false}
      reserveLink="/spaces/reservation/form"
      reservationTitle="Nova reserva"
    >
            
      { spaces.data.map( (space: Space) => {
        console.log(space)
        return       <div className="book-list">
        <ListItem 
            key={space.id}
            space={space}
            avatar={SpaceIcon}
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