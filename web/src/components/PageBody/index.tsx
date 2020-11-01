import React from 'react';

import { Link } from 'react-router-dom';

import addIcon from '../../assets/images/icons/add-white.svg';
import backIcon from '../../assets/images/icons/return.svg';

import './styles.css'

interface PageBodyProps {
    title: string;
    link?: string;
    isForm?: boolean;
}

const  PageBody: React.FC<PageBodyProps> = ({ title, link, isForm, children}) => {
    return (
        <div className="container">
            <article className="page-body">
               <header className="top-header">
                    <div className="top-bar-container">
                        <h1>{title}</h1>
                        
                        {!isForm && link && (
                            <Link to= {link}>
                                <img src={addIcon} alt="Novo cadastro"/>
                                Novo Cadastro
                            </Link>
                        )}

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