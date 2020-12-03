import React, { useEffect, useRef } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormHandles, SubmitHandler } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

// import { Document } from '../../utils/Document';
import Input from '../../components/Input';
import PageBody from '../../components/PageBody';
import FormBody from '../../components/FormBody';
import SaveForm from '../../components/SaveForm';


import {  addEvent, updateEvent } from '../../store/modules/events/actions';
import { errorRegister, successRegister, successUpdate } from '../../utils/notifications';
import { Event } from '../../store/modules/events/types';

import api from '../../services/api';

import { ApplicationState } from '../../store';

import './styles.css';

// const optionsCursos = [
//   { value: 1, label: 'Psicologia'},
//   { value: 2, label: 'Sistemas de Informação'},
// ]

function EventsForm(){


    const dispatch = useDispatch();
    const formRef = useRef<FormHandles>(null);

    const { events } = useSelector( (state: ApplicationState) => state); 

    const handleSubmit: SubmitHandler<Event> = async (data , { reset })=> {
      


      try{
        formRef.current?.setErrors({});

        const schemaDoc = Yup.object().shape({        
            title: Yup.string().required('O preenchimento do titulo é obrigatório'),
            horarioInicio: Yup.string().required('O preenchimento do Horario de Inicio é obrigatório'),
            horarioTermino: Yup.string().required('O preenchimento do Horario de Termino é obrigatório'),
            date: Yup.string().required('O preenchimento da data é obrigatório'),
            status: Yup.string().required('O preenchimento do status é obrigatório'),
          })
  
        await schemaDoc.validate(data, {
          abortEarly: false,
        })

        console.log('IS_EDIT', events.isEdit)

        if(events.isEdit){
          const { id } = events.data[0]

          data.id = id;
          console.log('FormData', events.formData)

          api.put(`eventos/${id}`, {
            "title": data.title,
            "date": "10-11-2021",
            "horarioInicio": data.horarioInicio+":00",
            "horarioTermino": data.horarioTermino+":00",
            "status": data.status
          }).then(() =>{
            successUpdate();
            reset()

            dispatch(updateEvent(data));
          }).catch(() => {
            errorRegister();
          })


        }else{
          console.log(data)

          api.post('eventos', {
            "title": data.title,
            "date": "10-11-2021",
            "horarioInicio": data.horarioInicio+":00",
            "horarioTermino": data.horarioTermino+":00",
            "status": data.status
          }
          ).then(() =>{
            successRegister();
            reset()

            dispatch(addEvent(data))
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
            title="Eventos- Formulário"
            isForm={true}
            link="/events"
        >
            <FormBody  title="Dados do Evento" >
                <Form onSubmit={handleSubmit} ref={formRef} initialData={ events.formData}>
                <Input 
                        name="title" 
                        label="Titulo"
                    />

                    <Input 
                        name="date" 
                        label="Data" 
                        type="date"
                    />

                    <Input 
                        name="horarioInicio" 
                        label="Horario de Inicio"
                        type="time"
                    />

                    <Input 
                        name="horarioTermino" 
                        label="Horario de Fechamento"
                        type="time"
                    />
                    
                    <Input
                        name="status"
                        label="Status"
                    />


                    <SaveForm title="Salvar Cadastro"/>
                </Form>
            </FormBody>


            
        </PageBody>
    )
}

export default EventsForm;