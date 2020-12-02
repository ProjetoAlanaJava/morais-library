import React from 'react';

import './styles.css';

interface FormProps {
  title: string;

}


const FormBody: React.FC<FormProps> = ({ title, children }) => {

  return (

    <div id="page-form">                      
      <main>
          <fieldset>
              <legend>{ title }</legend>
                { children }
          </fieldset>
      </main>
    </div>

  )
}

export default FormBody;