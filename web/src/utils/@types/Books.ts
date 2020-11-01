export interface Book {
  id: number;
  titulo: String;
  autores: Autor [];
  assunto: string;
  qtd_geral: number;
  ano_publicacao: number;
  editora: Editora;
  tipo: Tipo;
  categoria: Categoria;
  isbn: string; 
}


export interface Autor {
  id: number;
  name: string;
  bio: string;
  pais: string;
}

export interface Editora {
  id: number;
  nome: string;
  cnpj: string;
  telefone: string;
  email: string;
  pais: string;
}

export interface Categoria {
  id: number;
  nome: string;
  descricao: string;
}

export interface Tipo {
  id: number;
  nome: string;
}