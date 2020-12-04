import React from 'react';
import { useSelector } from 'react-redux';

import { Link } from 'react-router-dom';

import { ApplicationState } from '../../store';
import { logout } from '../../services/auth';

import createRelatorios from '../../utils/Relatorios';

import logoutIcon from '../../assets/images/icons/logout.svg';
import logoExtendIcon from '../../assets/images/icons/logo-extend.svg';

import './styles.css';


function Navbar(){

    
    const { login } = useSelector( (state: ApplicationState) => state);

    const handleLogout = () => {
      console.log('LOGOUT');
      logout()
    }

    const generateReports = ()=> {
        createRelatorios()
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
                    {login.data?.usuario.authority === 'funcionario' && (
                        <li className="menu-item">
                            <Link to="/users">Usuários</Link>
                        </li>
                    )}
                    {login.data?.usuario.authority === 'funcionario' && (
                        <li className="menu-item">
                            <Link to="/events">Eventos</Link>
                        </li>
                    )}

                    <li className="menu-item">
                        <Link to="/spaces">Espaços</Link>
                    </li>

                    {login.data?.usuario.authority === 'aluno' && (
                        <li className="menu-item">
                            <Link to="/ficha-catalografica/solicitacao">Ficha Catalográfica</Link>
                        </li>
                    )}

                    {login.data?.usuario.authority === 'funcionario' && (
                        <li className="menu-item">
                            <Link to="/ficha-catalografica">Ficha Catalográfica</Link>
                        </li>
                    )}

                    <li className="menu-item">
                        <Link to="/" onClick={generateReports}>Relatórios</Link>
                    </li>
                </ul>
            </nav>
            <div className="logout">
                <span>Olá, {login.data?.usuario.nome}</span>
                <Link to="#">
                    <img src={logoutIcon} alt="logout" onClick={handleLogout}/>
                </Link>
            </div>
        </header>
      
    )
}

export default Navbar;