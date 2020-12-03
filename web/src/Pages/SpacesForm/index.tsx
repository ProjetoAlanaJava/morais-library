import React, { useEffect, useRef, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormHandles, SubmitHandler } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

// import { Document } from '../../utils/Document';
import {  Space } from '../../store/modules/spaces/types'
import Input from '../../components/Input';
import PageBody from '../../components/PageBody';
import FormBody from '../../components/FormBody';
import Select from '../../components/Select';
import SaveForm from '../../components/SaveForm';


import { addSpace, updateSpace } from '../../store/modules/spaces/actions';
import { errorRegister, successRegister, successUpdate } from '../../utils/notifications';
import api from '../../services/api';

import { ApplicationState } from '../../store';

import './styles.css';

// const optionsCursos = [
//   { value: 1, label: 'Psicologia'},
//   { value: 2, label: 'Sistemas de Informação'},
// ]

function SpacesForm(){

    const [ optionsCursos ] = useState([
      { value: 1, label: 'Psicologia'},
      { value: 2, label: 'Sistemas de Informação'},
    ])

    const [ optionsAuthority ] = useState([
      { value: 0, label: 'Aluno'},
      { value: 1, label: 'Usuário Externo'},
      { value: 2, label: 'Professor'},
      { value: 3, label: 'Funcionário'},
    ])

    const dispatch = useDispatch();
    const formRef = useRef<FormHandles>(null);

    const { spaces } = useSelector( (state: ApplicationState) => state); 

    const handleSubmit: SubmitHandler<Space> = async (data , { reset })=> {
      


      try{
        formRef.current?.setErrors({});

        const schemaDoc = Yup.object().shape({        
            nome: Yup.string().required('O preenchimento da nome é obrigatório'),
            setor: Yup.string().required('O preenchimento do setor é obrigatório'),
            andar: Yup.string().required('O preenchimento do andar é obrigatório'),
            horarioAbertura: Yup.string().required('O preenchimento do Horario de Abertura é obrigatório'),
            horarioFechamento: Yup.string().required('O preenchimento do Horario de Fechamento é obrigatório'),
            tipo: Yup.string().required('O preenchimento do tipo do espaço é obrigatório'),
            capacidade: Yup.string().required('O preenchimento da capacidade é obrigatório'),
          })
  
        await schemaDoc.validate(data, {
          abortEarly: false,
        })

        console.log('IS_EDIT', spaces.isEdit)

        if(spaces.isEdit){
          const { id } = spaces.data[0]

          data.id = id;
          console.log('FormData', spaces.formData)

          api.put(`espacos/${id}`, {
            "nome": data.nome,
            "setor": data.setor,
            "andar": data.andar,
            "horarioAbertura": data.horarioAbertura+":00",
            "horarioFechamento": data.horarioFechamento+":00",
            "tipo": data.tipo,
            "capacidade": data.capacidade
          }).then(() =>{
            successUpdate();
            reset()

            dispatch(updateSpace(data));
          }).catch(() => {
            errorRegister();
          })


        }else{
          console.log(data)

          api.post('espacos', {
            "nome": data.nome,
            "setor": data.setor,
            "andar": data.andar,
            "horarioAbertura": data.horarioAbertura+":00",
            "horarioFechamento": data.horarioFechamento+":00",
            "tipo": data.tipo,
            "capacidade": data.capacidade
          }
          ).then(() =>{
            successRegister();
            reset()

            dispatch(addSpace(data))
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
            link="/spaces"
        >
            <FormBody  title="Dados do Documento" >
                <Form onSubmit={handleSubmit} ref={formRef} initialData={ spaces.formData}>
                    <Input 
                        name="nome" 
                        label="Nome"
                    />

                    <Input 
                        name="setor" 
                        label="Setor" 
                    />

                    <Input 
                        name="andar" 
                        label="Andar" 
                    />

                    <Input 
                        name="horarioAbertura" 
                        label="Horario de Abertura"
                        type="time"
                    />

                    <Input 
                        name="horarioFechamento" 
                        label="Horario de Fechamento"
                        type="time"
                    />


                    <Input 
                        name="tipo" 
                        label="Tipo da sala" 
                    />
                    
                    <Input
                        name="capacidade"
                        label="capacidade"
                    />


                    <SaveForm title="Salvar Cadastro"/>
                </Form>
            </FormBody>


            
        </PageBody>
    )
}

export default SpacesForm;