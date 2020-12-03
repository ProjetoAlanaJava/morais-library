import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import PageBody from '../../components/PageBody';
import ListItem from '../../components/ListItem';

import calendarIcon from '../../assets/images/icons/calendario.svg';

import { ApplicationState } from '../../store';
import { loadEventsRequest } from '../../store/modules/events/actions';
import { Event } from '../../store/modules/events/types';

import './styles.css';

function EventsList(){

  const dispatch = useDispatch()

  const { events } = useSelector( (state: ApplicationState) => state);

  useEffect(() => {
    dispatch(loadEventsRequest())
  }, [dispatch]);
  

  return (
    <PageBody 
      title="Eventos - Lista"
      link="/events/form"
      isForm={false}
    >
            
      { events.data.map( (event: Event) => {
        console.log(event)
        return       <div className="events-list">
        <ListItem 
            key={event.id}
            type="event"
            event={event}
            avatar={calendarIcon}
            header={event.title}
            description_one_title="Data"
            description_one_value={event.date}
            description_two_title="Horario de início"
            description_two_value={event.horarioInicio}
            additional_information_title="Status"
            additional_information_value={event.status}
            editLink="/events/form"
            deleteLink="events"
        />
      </div>
      })}


    </PageBody>
  )
}

export default EventsList;