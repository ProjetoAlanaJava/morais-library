export interface User{
  id: number;
  nome: string;
  cpf: string;
  ativo: boolean;
  curso: Curso [];
  departamento: Departamento;
  tipo: string;
  limiteLivros: number;
  telefone?: string;
  email: string;
}


export interface Curso {
  id: number;
  nome: string;
  area: string;
  tipo: string;
}

export interface Departamento {
  id: number;
  nome: string;
  area: string;
}