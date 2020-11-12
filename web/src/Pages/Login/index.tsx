import React, { useRef } from 'react';
import { FormHandles, SubmitHandler } from '@unform/core';
import { Form } from '@unform/web';
import { useHistory } from 'react-router-dom';
import * as Yup from 'yup';

import Input from '../../components/Input';
import SaveForm from '../../components/SaveForm';

// import api from '../../services/api';
// import { login } from '../../services/auth';
// import { errorLogin } from '../../utils/Notifications';

import { login } from '../../services/auth';

import './styles.css';
// import { User } from '../../store/modules/login/types';
// import { setUser } from '../../store/modules/login/actions';


interface LoginData {
  username: string;
  password: string;
}

function Login() {

  const history = useHistory();
  const formRef = useRef<FormHandles>(null);

  const handleSubmit: SubmitHandler<LoginData> = async data => {

    try{
      formRef.current?.setErrors({})

      const schemaDoc = Yup.object().shape({        
              username: Yup.string().required('Por favor, digite o nome de usuário'),
              password: Yup.string().required('Por favor, digite a senha'),
        })

      await schemaDoc.validate(data, {
        abortEarly: false,
      })

      login("TOKEN_PROVISORIO");
       
      history.push('/books');

    }catch(err){
      if(err instanceof Yup.ValidationError){
          err.inner.forEach( error => {
              formRef.current?.setFieldError(error.path, error.message)
            }) 
      } 
    }
  }

  return (
    <div className="container">
      <article className="login-body">
        <header className="login-header">
              <div className="login-container">
                  <fieldset>
                    <legend><h2>Login</h2></legend>
                  </fieldset>
                  
                  <Form onSubmit={handleSubmit} ref={formRef}>
                    <Input name="username" label="Username"></Input>
                    <Input name="password" label="Senha" type="password"></Input>
                    <SaveForm title="Acessar"/>
                  </Form>

              </div>
        </header>      
      </article>
    </div>
  )
}

export default Login;