import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';

export const errorLogin = () => toast.error('Falha na autenticação, verifique seus dados');
export const errorRegister = () => toast.error('Falha ao efetuar o registro, verifique os dados preenchidos');
export const errorUpdate = () => toast.error('Falha ao atualizar o registro, verifique os dados preenchidos');
export const successRegister = () => toast.success('Cadastro efetuado com sucesso!');
export const successReservation = () => toast.success('Reserva efetuada com sucesso!');
export const errorReservation = () => toast.error('Error ao efetuar a reserva, contate o suporte do Sistema!');
export const successCustom = (message: string) => toast.success(message);
export const errorCustom = (message: string) => toast.error(message);
export const successUpdate = () => toast.success('Cadastro atualizado com sucesso!');