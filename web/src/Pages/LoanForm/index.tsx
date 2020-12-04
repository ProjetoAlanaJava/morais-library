import React, { useRef } from 'react';
import { useSelector } from 'react-redux';
import { FormHandles, SubmitHandler } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

// import { Document } from '../../utils/Document';
import {  Space } from '../../store/modules/spaces/types'
import Input from '../../components/Input';
import PageBody from '../../components/PageBody';
import FormBody from '../../components/FormBody';
import SaveForm from '../../components/SaveForm';


import { addSpace, updateSpace } from '../../store/modules/spaces/actions';
import { errorRegister, successRegister, successUpdate } from '../../utils/notifications';
import api from '../../services/api';

import { ApplicationState } from '../../store';

import './styles.css';
import Select from '../../components/Select';

const optionsUsers = [
  { value: 11, label: 'Caio Henrique'},
  { value: 4, label: 'Gabriel Moreira de Oliveira'},
]
const optionsBooks = [
  { value: 2, label: 'Matemática Lógica'},
  { value: 3, label: 'Psicologia das cores'},
]

interface Emprestimo {
  book: string;
  user: string;
}


function LoanForm(){

    const formRef = useRef<FormHandles>(null);

    const { login } = useSelector( (state: ApplicationState) => state);


    const handleSubmit: SubmitHandler<Emprestimo> = async (data , { reset })=> {
      


      try{
        formRef.current?.setErrors({});

        api.get(`auth/usuarios/${data.user}`, 
        ).then((response ) =>{
          console.log(response)

          api.post('emprestimo', {
            "usuario": { "id": response.data.id, "matricula": response.data.matricula},
            "funcionario": { "id": login.data?.usuario.id, "matricula": login.data?.usuario.matricula} ,
            "livro": { "id": data.book},
          }
          ).then(() =>{
            successRegister();
            reset()

          }).catch(() => {
            errorRegister();
          })

        }).catch(() => {
          errorRegister();
        })



        console.log(data)
        
      }catch(err){
        console.log(err)
      }
    }


    return (
        <PageBody 
            title="Empréstimo - Formulário"
            isForm={true}
            link="/books"
        >
            <FormBody  title="Dados do emprestimo" >
                <Form onSubmit={handleSubmit} ref={formRef} >

                    <Select
                        name="user" 
                        label="Usuário"
                        options={optionsUsers}
                    />
                    <Select 
                        name="book" 
                        label="Livro"
                        options={optionsBooks}
                    />


                    <SaveForm title="Salvar Cadastro"/>
                </Form>
            </FormBody>


            
        </PageBody>
    )
}

export default LoanForm;