import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';

export const errorLogin = () => toast.error('Falha na autenticação, verifique seus dados');
export const errorRegister = () => toast.error('Falha ao efetuar o registro, verifique os dados preenchidos');
export const errorUpdate = () => toast.error('Falha ao atualizar o registro, verifique os dados preenchidos');
export const successRegister = () => toast.success('Cadastro efetuado com sucesso!');
export const successUpdate = () => toast.success('Cadastro atualizado com sucesso!');