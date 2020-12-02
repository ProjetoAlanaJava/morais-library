import React, { useEffect, useRef, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormHandles, SubmitHandler } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

// import { Document } from '../../utils/Document';
import { Curso, User } from '../../store/modules/users/types'
import Input from '../../components/Input';
import PageBody from '../../components/PageBody';
import FormBody from '../../components/FormBody';
import Select from '../../components/Select';
import SaveForm from '../../components/SaveForm';


import {  addUser, updateUser } from '../../store/modules/users/actions';
import { errorRegister, successRegister, successUpdate } from '../../utils/notifications';
import api from '../../services/api';

import { ApplicationState } from '../../store';

import './styles.css';

// const optionsCursos = [
//   { value: 1, label: 'Psicologia'},
//   { value: 2, label: 'Sistemas de Informação'},
// ]

function UserForm(){

    const [ optionsCursos, setOptionsCursos ] = useState([
      { value: 1, label: 'Psicologia'},
      { value: 2, label: 'Sistemas de Informação'},
    ])

    const [ optionsAuthority, setOptionsAuthority ] = useState([
      { value: 0, label: 'Aluno'},
      { value: 1, label: 'Usuário Externo'},
      { value: 2, label: 'Professor'},
      { value: 3, label: 'Funcionário'},
    ])

    const dispatch = useDispatch();
    const formRef = useRef<FormHandles>(null);

    const { users } = useSelector( (state: ApplicationState) => state); 

    const handleSubmit: SubmitHandler<User> = async (data , { reset })=> {
      


      try{
        formRef.current?.setErrors({});

        const schemaDoc = Yup.object().shape({        
            matricula: Yup.string().required('O preenchimento da matrícula é obrigatório'),
            nome: Yup.string().required('O preenchimento do nome é obrigatório'),
            cpf: Yup.string().required('O preenchimento do cpf é obrigatório'),
            email: Yup.string().required('O preenchimento do Email é obrigatório'),
            telefone: Yup.string().required('O preenchimento do telefone é obrigatório'),
          })
  
        await schemaDoc.validate(data, {
          abortEarly: false,
        })

        console.log('IS_EDIT', users.isEdit)

        if(users.isEdit){
          const { id } = users.data[0]

          data.id = id;

          // api.put(`document/${_id}`, data).then(() =>{
          //   successUpdate();
          //   reset()

          //   dispatch(updateUser(data));
          // }).catch(() => {
          //   errorRegister();
          // })


        }else{
          console.log(data)

          api.post('auth/signup', {
            "matricula" : data.matricula,
            "password":  data.cpf,
            "authority" : optionsAuthority[data.authority].label,
            "nome": data.nome,
            "cpf": data.cpf,
            "curso": {"id": data.curso},
            "cargo": data.cargo, 
            "telefone": data.telefone,
            "email": data.email
          }
          ).then(() =>{
            successRegister();
            reset()

            dispatch(addUser(data))
          }).catch(() => {
            errorRegister();
          })

        }
  


        console.log(data)
        
      }catch(err){
        if(err instanceof Yup.ValidationError){

          err.inner.forEach( error => {
            formRef.current?.setFieldError(error.path, error.message)
          })   
        }
      }
    }

    useEffect(()=>{

    api.get('cursos', 
      ).then((cursos) =>{
        // cursos.data.map((curso: Curso) => {
        //   setOptionsCursos((prevState)=> [
        //     ...prevState,
        //     {
        //       value: curso.id,
        //       label: curso.nome
        //     }
        //   ])
        // })
        // cursos.data.map((data:any) =>{
        //   return console.log(data)
        // })
        console.log(cursos.data)
      }).catch(() => {
        console.log('ERROR LOADING COURSES')
      })
    }, [])

    return (
        <PageBody 
            title="Usuários - Formulário"
            isForm={true}
            link="/users"
        >
            <FormBody  title="Dados do Documento" >
                <Form onSubmit={handleSubmit} ref={formRef} initialData={ users.formData}>
                    <Input 
                        name="matricula" 
                        label="Matrícula"
                    />

                    <Input 
                        name="nome" 
                        label="Nome" 
                    />

                    <Input 
                        name="cpf" 
                        label="CPF" 
                    />

                    <Input 
                        name="email" 
                        label="Email" 
                    />

                    <Select 
                      name="authority" 
                      label="Permissão"
                      options={optionsAuthority}
                    />

                    <Input 
                        name="cargo" 
                        label="Cargo (*)" 
                    />

                    <Select 
                        name="curso" 
                        label="Curso"
                        options={optionsCursos}
                    />

                    
                    <Input
                        name="telefone"
                        label="Telefone"
                    />


                    <SaveForm title="Salvar Cadastro"/>
                </Form>
            </FormBody>


            
        </PageBody>
    )
}

export default UserForm;