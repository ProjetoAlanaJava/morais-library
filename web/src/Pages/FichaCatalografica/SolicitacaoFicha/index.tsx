import React, { useRef } from 'react';
import { useSelector } from 'react-redux';
import { FormHandles, SubmitHandler } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

// import { Document } from '../../utils/Document';
import Input from '../../../components/Input';
import PageBody from '../../../components/PageBody';
import FormBody from '../../../components/FormBody';
import SaveForm from '../../../components/SaveForm';


// import {  addEvent, updateEvent } from '../../../store/modules/events/actions';
// import { errorRegister, successRegister, successUpdate } from '../../../utils/notifications';

// import api from '../../../services/api';

import { ApplicationState } from '../../../store';

import './styles.css';
import { FichaCatalografica } from '../../../store/modules/fichaCatalografica/types';
import api from '../../../services/api';
import { errorRegister, successRegister } from '../../../utils/notifications';

// const optionsCursos = [
//   { value: 1, label: 'Psicologia'},
//   { value: 2, label: 'Sistemas de Informação'},
// ]

function SolicitacaoFicha(){


    // const dispatch = useDispatch();
    const formRef = useRef<FormHandles>(null);

    const { events, login } = useSelector( (state: ApplicationState) => state); 

    const handleSubmit: SubmitHandler<FichaCatalografica> = async (data , { reset })=> {
      


      try{
        formRef.current?.setErrors({});

        const schemaDoc = Yup.object().shape({        
            titulo: Yup.string().required('O preenchimento do titulo é obrigatório'),
            subtitulo: Yup.string().required('O preenchimento do subtitulo é obrigatório'),
            assuntos: Yup.string().required('O preenchimento do assunto é obrigatório'),
            isbn: Yup.string().required('O preenchimento do ISBN é obrigatório'),
            dataPublicacao: Yup.string().required('O preenchimento da data de publicação é obrigatório'),
            localPublicacao: Yup.string().required('O preenchimento do local de publicação é obrigatório'),
            numPaginas: Yup.string().required('O preenchimento do numero de páginas é obrigatório'),
          })
  
        await schemaDoc.validate(data, {
          abortEarly: false,
        })

        console.log(data)

          api.post(`autores`, {
              "name": login.data?.usuario.nome,
              "bio": "",
              "pais": "Brasil"
          }).then((response: any) =>{

            console.log(response.data.id)

            api.post('ficha-catalografica/solicitar-ficha', {
                "titulo" : data.titulo,
                "subtitulo" : data.subtitulo,
                "autor": { "id": response.data.id},
                "assuntos": data.assuntos,
                "isbn" : data.isbn,
                "editora": {"id": data.idEditora},
                "localPublicacao" : data.localPublicacao,
                "dataPublicacao" : data.dataPublicacao,
                "numPaginas" : data.numPaginas
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
            title="Solicitação - Ficha Catalográfica"
            isForm={true}
            // link="/events"
        >
            <FormBody  title="Dados da Ficha" >
                <Form onSubmit={handleSubmit} ref={formRef} initialData={ events.formData}>
                    <Input 
                      name="titulo" 
                      label="Titulo"
                    />
                    <Input 
                      name="subtitulo" 
                      label="Subtítulo"
                    />

                    <Input 
                      name="assuntos" 
                      label="Assuntos"
                    />

                    <Input 
                      name="isbn" 
                      label="ISBN"
                    />

                    <Input 
                      name="dataPublicacao" 
                      label="Data da Publicacao" 
                      type="date"
                    />

                    <Input 
                      name="idEditora" 
                      label="Id da Editora" 
                    />

                    <Input 
                        name="localPublicacao" 
                        label="Local de Publicação"
                    />
                    
                    <Input
                        name="numPaginas"
                        label="Número de páginas"
                    />


                    <SaveForm title="Solicitar"/>
                </Form>
            </FormBody>


            
        </PageBody>
    )
}

export default SolicitacaoFicha;