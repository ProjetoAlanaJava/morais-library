import React from 'react';
import { useSelector } from 'react-redux';

import { Link } from 'react-router-dom';

import addIcon from '../../assets/images/icons/add-white.svg';
import backIcon from '../../assets/images/icons/return.svg';
import { ApplicationState } from '../../store';

import './styles.css'

interface PageBodyProps {
    title: string;
    link?: string;
    isForm?: boolean;
    reserveLink?: string;
    isReserve?: boolean;
    reservationTitle?: string;
    isBook?: boolean;
    myReservationsLink?: string;
    uploadTitle?: string;
    uploadLink?:string;
    isBookPage?: boolean;
}

const  PageBody: React.FC<PageBodyProps> = (
    { title, link, isForm, isReserve, 
      reservationTitle, reserveLink, isBook, myReservationsLink, uploadLink,
      uploadTitle, isBookPage, children 
    }) => {

    
    
    const { login } = useSelector( (state: ApplicationState) => state);


    return (
        <div className="container">
            <article className="page-body">
               <header className="top-header">
                    <div className="top-bar-container">
                        <h1>{title}</h1>
                        
                        <div className="button-block">
                            {isReserve && reserveLink && (
                                <Link to={reserveLink}>
                                    <img src={addIcon} alt={reservationTitle}/>
                                    {reservationTitle}
                                </Link>
                            )}
                            {isBook && myReservationsLink && (login.data?.usuario.authority !== 'funcionario') && (
                                <Link to= {myReservationsLink}>
                                    Minhas reservas
                                </Link>
                            )}
                            {login.data?.usuario.authority === 'funcionario' &&
                            isBookPage && (
                                <Link to='loans/form'>
                                    Emprestimos
                                </Link>
                            )}

                            {uploadLink && uploadTitle && (
                                <Link to= {uploadLink}>
                                    {uploadTitle}
                                </Link>
                            )}

                            {!isForm && link && !isBook && (
                                <Link to= {link}>
                                    <img src={addIcon} alt="Novo Cadastro"/>
                                    Novo Cadastro
                                </Link>
                            )}
                        </div>

                        { isForm && link && (
                            <Link to= {link}>
                                <img src={backIcon} alt="retornar"/>
                                Voltar
                            </Link>
                        )}
                        
                        

                    </div>



               </header>
               
               
                {children}
            </article>
 
        </div>
    )
}

export default PageBody;