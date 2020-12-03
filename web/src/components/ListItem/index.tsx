import React from 'react';
import { useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';

import { User } from '../../store/modules/users/types';
import { Space } from '../../store/modules/spaces/types';
import { Event } from '../../store/modules/events/types';
import { deleteUser, showUser } from '../../store/modules/users/actions';
import { deleteSpace, showSpace } from '../../store/modules/spaces/actions';
import { deleteEvent, showEvent } from '../../store/modules/events/actions';

import addIcon from '../../assets/images/icons/add-white.svg';
import deleteIcon from '../../assets/images/icons/delete-white.svg';
import editIcon from '../../assets/images/icons/edit-white.svg';

import api from '../../services/api';


import './styles.css';


interface ListItemProps {
    avatar?: string;
    key: number;
    editLink: string;
    deleteLink: string;
    user?: User ;
    space?: Space;
    event?: Event;
    isBook?: boolean;
    reserveLink?: string;
    type: string;
    header: string;
    description_one_value: string | Date | any;
    description_one_title: string; 
    description_two_value: string;
    description_two_title: string;
    additional_information_title: string
    additional_information_value: any;
}

const ListItem: React.FC<ListItemProps> = (
    {   editLink, deleteLink, avatar, description_one_value, description_one_title, 
        description_two_value, description_two_title, additional_information_value, 
        additional_information_title, header, type, user, space, event, reserveLink,
        isBook
    }) =>{

    const dispatch = useDispatch();

    function editItem(){

        if(user){
            dispatch(showUser(user))
        }

        if(space){
            dispatch(showSpace(space))
        }

        if(event){
            dispatch(showEvent(event))
        }

        return console.log('Edit Link ')
    }

    function deleteItem(){
        const confirmDelete = window.confirm('Você deseja realmente excluir esse registro?')

        if(confirmDelete){
            if(user){
                api.delete(`auth/delete/${user.id}`);
                dispatch(deleteUser(user.id))
                // dispatch(loadUsersRequest())
            }
            if(space){
                api.delete(`espacos/${space.id}`);
                dispatch(deleteSpace(space.id))
                // dispatch(loadUsersRequest())
            }
            if(event){
                api.delete(`eventos/${event.id}`);
                dispatch(deleteEvent(event.id))
                // dispatch(loadUsersRequest())
            }
        }
        return console.log('Delete Link')
    }

    function reserveItem(){
        console.log('RESERVE LINK')
    }

    return (
        <main className="list-item">
            { avatar?
                <div className="avatar">
                    <img src={avatar} alt=""/>
                </div> 
                : null
            }
            <article className="content">
                <header>
                    <strong>{ header }</strong>
                    <br/>              
                </header>
                <div className="body-content">
                    <strong>{ description_one_title}</strong>
                    <span>{ description_one_value }</span>
                    <br/>
                    <strong>{ description_two_title}</strong>
                    <span>{description_two_value}</span>
                    <br/>
                    <strong>{ additional_information_title}</strong>
                    <span>{ additional_information_value}</span>
                </div>
            </article>

            <div className="options">
                {!isBook && (
                    <>
                        <Link to={editLink}>
                            <img src={editIcon} alt="Editar item" onClick={editItem}/>
                        </Link>      
                        
                        <Link to={deleteLink}>
                            <img src={deleteIcon} alt="Deletar item" onClick={deleteItem}/>
                        </Link>
                    </>
                )}

                { reserveLink && (
                    <Link to={reserveLink} id="reserve-link" onClick={reserveItem}>
                        <img src={addIcon} alt="Nova reserva" />
                        Reservar 
                    </Link>    
                )}
            </div>
        </main>
    )
}

export default ListItem;