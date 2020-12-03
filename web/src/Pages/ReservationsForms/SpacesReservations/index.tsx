import React, { useRef } from 'react';
// import { useDispatch, useSelector } from 'react-redux';
import { FormHandles, SubmitHandler } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

// import { Document } from '../../utils/Document';
import { SpaceReservation } from '../../../store/modules/spaces/types'
import Input from '../../../components/Input';
import PageBody from '../../../components/PageBody';
import FormBody from '../../../components/FormBody';
import SaveForm from '../../../components/SaveForm';


// import { addSpace, updateSpace } from '../../../store/modules/spaces/actions';
import { errorRegister, successRegister } from '../../../utils/notifications';
import api from '../../../services/api';

// import { ApplicationState } from '../../../store';

import './styles.css';

// const optionsCursos = [
//   { value: 1, label: 'Psicologia'},
//   { value: 2, label: 'Sistemas de Informação'},
// ]

function SpacesReservationsForm(){

    // const dispatch = useDispatch();
    const formRef = useRef<FormHandles>(null);

    // const { spaces } = useSelector( (state: ApplicationState) => state); 

    const handleSubmit: SubmitHandler<SpaceReservation> = async (data , { reset })=> {

      try{
        formRef.current?.setErrors({});

        const schemaDoc = Yup.object().shape({        
            data: Yup.string().required('O preenchimento da data é obrigatório'),
            horarioInicioReserva: Yup.string().required('O preenchimento do Horario de Inicio é obrigatório'),
            horarioFimReserva: Yup.string().required('O preenchimento do Horario de Termino é obrigatório'),
          })
  
        await schemaDoc.validate(data, {
          abortEarly: false,
        })


        console.log(data)

        api.post('reserva-livro', {
          "usuario": { "id": 1},
          "date": data.data,
          "espaco": { "id": 2},
          "evento": null,
          "horarioInicioReserva": data.horarioInicioReserva+":00",
          "horarioFimReserva": data.horarioFimReserva+":00",
        }
        ).then(() =>{
          successRegister();
          reset()

          // dispatch(addEvent(data))
        }).catch(() => {
          errorRegister();
        })
        
      }catch(err){
        if(err instanceof Yup.ValidationError){

          err.inner.forEach( error => {
            formRef.current?.setFieldError(error.path, error.message)
          })   
        }
      }
    }

    return (
        <PageBody 
            title="Reserva -  Sala de Estudo"
            isForm={true}
            link="/spaces"
        >
            <FormBody  title="Dados da Reserva" >
                <Form onSubmit={handleSubmit} ref={formRef} >

                    <Input 
                        name="data" 
                        label="Data"
                        type="date" 
                    />

                    <Input 
                        name="horarioInicioReserva" 
                        label="Horario de Inicio"
                        type="time"
                    />

                    <Input 
                        name="horarioFimReserva" 
                        label="Horario de Termino"
                        type="time"
                    />

                    <SaveForm title="Reservar"/>
                </Form>
            </FormBody>


            
        </PageBody>
    )
}

export default SpacesReservationsForm;