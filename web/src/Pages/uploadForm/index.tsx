import React, {  useRef } from 'react';
import { useDispatch } from 'react-redux';
import { FormHandles, SubmitHandler } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

// import { Document } from '../../utils/Document';
// import {  Space } from '../../store/modules/spaces/types'
import Input from '../../components/Input';
import PageBody from '../../components/PageBody';
import FormBody from '../../components/FormBody';
import SaveForm from '../../components/SaveForm';


// import { addSpace, updateSpace } from '../../store/modules/spaces/actions';
import { errorRegister, successRegister } from '../../utils/notifications';
import api from '../../services/api';

import { User } from '../../store/modules/users/types';
// import { ApplicationState } from '../../store';

import './styles.css';
import { addUser } from '../../store/modules/users/actions';


function UploadsForm(){

    let fileToSendApi: User [];
    let fileContent : string;
    let success : boolean;
    
    const dispatch = useDispatch();
    const formRef = useRef<FormHandles>(null);
    // const [uploadFile, setUploadFile] = useState();


    const handleUploadFile = async (e: any) => {
      // setCardFile(e.target.files[0]);
      console.log('TARGET',e.target.files[0])
      let fileReader;
      fileReader = new FileReader();
      fileReader.onload = async (e) =>{
          console.log('DATA READER ',e.target?.result)
           if(e.target?.result){
            fileContent = e.target.result.toString();
           }

          
        }
      fileReader.readAsText(e.target.files[0])
    }

    // const { spaces } = useSelector( (state: ApplicationState) => state); 

    const handleSubmit: SubmitHandler = async (data , { reset })=> {
      


      try{
        formRef.current?.setErrors({});

        const schemaDoc = Yup.object().shape({        
            arquivo: Yup.string().required('O preenchimento do arquivo é obrigatório'),
          })
  
        await schemaDoc.validate(data, {
          abortEarly: false,
        })
      
      fileToSendApi = JSON.parse(fileContent)
      
      // console.log(fileToSendApi)
      fileToSendApi.map( async (user: User) => {
        return          api.post('auth/signup', {
          "matricula" : user.matricula,
          "password":  user.cpf,
          "authority" : user.authority,
          "nome": user.nome,
          "cpf": user.cpf,
          "curso": user.curso? {"id": user.curso}: null,
          "cargo": user.cargo ? user.cargo : null, 
          "telefone": user.telefone,
          "email": user.email
        }
        ).then(() =>{
          // successRegister();
          console.log('Cadastro realizado: ', user)
          success = true;
          reset()
          dispatch(addUser(data))
        }).catch(() => {
          success= false;
          errorRegister();
        })
      })

      if(success){ 
        successRegister()
      }
        
        
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
            title="Uploads"
            isForm={true}
        >
            <FormBody  title="Inportar Novos Usuários" >
                <Form onSubmit={handleSubmit} ref={formRef} >
                    <Input 
                        name="arquivo" 
                        label="Arquivo"
                        type="file" 
                        accept="application/json"
                        onChange={handleUploadFile}  
                    />

                    <SaveForm title="Enviar"/>
                </Form>
            </FormBody>


            
        </PageBody>
    )
}

export default UploadsForm;