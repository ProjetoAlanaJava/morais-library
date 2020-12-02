import { action } from 'typesafe-actions';
import { Event, EventsTypes } from './types';

export const loadEventsRequest = () => action( EventsTypes.LOAD_EVENTS_REQUEST);
export const loadEvents = (data: Event []) => action( EventsTypes.LOAD_EVENTS, { data } );
