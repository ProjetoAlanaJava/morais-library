import React from 'react';

import { Link } from 'react-router-dom';

import deleteIcon from '../../assets/images/icons/delete-white.svg';
import editIcon from '../../assets/images/icons/edit-white.svg';

// import api from '../../services/api';

import { Book } from '../../utils/@types/Books';
import { User } from '../../utils/@types/Users';


import './styles.css';

interface ListItemProps {
    avatar?: string;
    key: number;
    titulo: string;
    description: string;
    editLink: string;
    deleteLink: string;
    book?: Book ;
    user?: User ;
}

const ListItem: React.FC<ListItemProps> = ({ titulo, description, editLink, deleteLink, avatar, book, user}) =>{

    function editItem(){
        return console.log('Edit Link')
    }

    function deleteItem(){
        const confirmDelete = window.confirm('Você deseja realmente excluir esse documento?')

        if(confirmDelete){
          return console.log('Delete Link')
        }

    }

    return (
        <section className="list-item">
            { avatar?
                <div className="avatar">
                    <img src={avatar} alt=""/>
                </div> 
                : null
            }
            <div className="content">
                <strong>
                    Igor Felipe Sales
                </strong>
                <span> Curso: Sistemas de Informação</span>
                <span>email: igor@igorsales.com.br</span>
                <span>telefone: (99) 99999-9999</span>
            </div>
            <div className="options">
                <Link to={editLink}>
                    <img src={editIcon} alt="Editar item" onClick={editItem}/>
                </Link>      
                
                <Link to={deleteLink}>
                    <img src={deleteIcon} alt="Deletar item" onClick={deleteItem}/>
                </Link>
            </div>
        </section>
    )
}

export default ListItem;