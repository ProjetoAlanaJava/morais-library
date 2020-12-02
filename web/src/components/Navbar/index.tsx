import React from 'react';

import { Link } from 'react-router-dom';


import logoutIcon from '../../assets/images/icons/logout.svg';
import logoExtendIcon from '../../assets/images/icons/logo-extend.svg';

import './styles.css';
import { logout } from '../../services/auth';

function Navbar(){

    const handleLogout = () => {
      console.log('LOGOUT');
      logout()
    }

    return (
        <header className="page-header">
            <Link to="/">
                <img src={logoExtendIcon} alt="Logo"/>
            </Link>
            <nav className="navbar">
                <ul className="menu">
                    <li className="menu-item">
                        <Link to="/books">Livros</Link>
                    </li>
                    <li className="menu-item">
                        <Link to="/users">Usuários</Link>
                    </li>
                    <li className="menu-item">
                        <Link to="/events">Eventos</Link>
                    </li>
                    <li className="menu-item">
                        <Link to="/settings">Configurações</Link>
                    </li>
                </ul>
            </nav>
            <div className="logout">
                <span>Olá, User</span>
                <Link to="#">
                    <img src={logoutIcon} alt="logout" onClick={handleLogout}/>
                </Link>
            </div>
        </header>
      
    )
}

export default Navbar;