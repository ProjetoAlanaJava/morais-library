import React from 'react';

import { Link } from 'react-router-dom';

import deleteIcon from '../../assets/images/icons/delete-white.svg';
import editIcon from '../../assets/images/icons/edit-white.svg';

// import api from '../../services/api';

import { Book } from '../../utils/@types/Books';
import { User } from '../../store/modules/users/types';


import './styles.css';

interface ListItemProps {
    avatar?: string;
    key: number;
    editLink: string;
    deleteLink: string;
    book?: Book ;
    user?: User ;
}

const ListItem: React.FC<ListItemProps> = ({ editLink, deleteLink, avatar, book, user }) =>{

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
        <main className="list-item">
            { avatar?
                <div className="avatar">
                    <img src={avatar} alt=""/>
                </div> 
                : null
            }
            <article className="content">
                <header>
                    <strong>{ user? user?.nome : 'Teste' }</strong>
                    <br/>              
                </header>
                <div className="body-content">
                    <strong>{ user? 'Curso:' : 'Editora:'}</strong>
                    <span>{ user? user?.curso.nome : 'Teste' }</span>
                    <br/>
                    <strong>{ user? 'Email:' : 'ISBN:'}</strong>
                    <span>{user? user?.email : 'Teste'}</span>
                    <br/>
                    <strong>{ user? 'Telefone:' : 'Quantidade:'}</strong>
                    <span>{ user? user?.telefone : 'Teste'}</span>
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