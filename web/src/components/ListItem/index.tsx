import React from 'react';
import { Link } from 'react-router-dom';

import { showUser } from '../../store/modules/users/actions';

import deleteIcon from '../../assets/images/icons/delete-white.svg';
import editIcon from '../../assets/images/icons/edit-white.svg';

// import api from '../../services/api';


import './styles.css';
import { User } from '../../store/modules/users/types';

interface ListItemProps {
    avatar?: string;
    key: number;
    editLink: string;
    deleteLink: string;
    user?: User ;
    type: string,
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
        additional_information_title, header, type, user
    }) =>{

    function editItem(){

        if(user){
            return console.log('EDIT - USUARIO', user)
        }
        return console.log('Edit Link generic')
    }

    function deleteItem(){
        const confirmDelete = window.confirm('Você deseja realmente excluir esse documento?')

        if(confirmDelete){
          return console.log('Delete Link')
        }

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
                <Link to={editLink}>
                    <img src={editIcon} alt="Editar item" onClick={editItem}/>
                </Link>      
                
                <Link to={deleteLink}>
                    <img src={deleteIcon} alt="Deletar item" onClick={deleteItem}/>
                </Link>
            </div>
        </main>
    )
}

export default ListItem;