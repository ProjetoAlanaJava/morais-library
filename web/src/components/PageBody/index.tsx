import React from 'react';

import { Link } from 'react-router-dom';

import addIcon from '../../assets/images/icons/add-white.svg';
import backIcon from '../../assets/images/icons/return.svg';

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
}

const  PageBody: React.FC<PageBodyProps> = (
    { title, link, isForm, isReserve, 
      reservationTitle, reserveLink, isBook, myReservationsLink, uploadLink,
      uploadTitle, children }) => {
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
                            {isBook && myReservationsLink && (
                                <Link to= {myReservationsLink}>
                                    Minhas reservas
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