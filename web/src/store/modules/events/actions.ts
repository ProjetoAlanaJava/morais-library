import { action } from 'typesafe-actions';
import { Event, EventsTypes } from './types';

export const addEvent = (data: Event) => action(EventsTypes.ADD_EVENT,  { data });
export const loadEventsRequest = () => action( EventsTypes.LOAD_EVENTS_REQUEST);
export const loadEvents = (data: Event []) => action( EventsTypes.LOAD_EVENTS, { data } );
export const showEvent = ( data: Event) => action(EventsTypes.SHOW_EVENT, { data });
export const updateEvent = ( data: Event ) => action(EventsTypes.UPDATE_EVENT, { data } );
export const deleteEvent = (id: number) => action(EventsTypes.DELETE_EVENT, { id });
