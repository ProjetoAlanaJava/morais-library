package br.com.projetojava.morais.library;


import br.com.projetojava.morais.library.model.Curso;
import br.com.projetojava.morais.library.model.Departamento;
import br.com.projetojava.morais.library.model.Usuario;
import br.com.projetojava.morais.library.repository.UsuarioRepository;

import br.com.projetojava.morais.library.service.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.projetojava.morais.library.controller.UsuarioController;

import java.util.ArrayList;
import java.util.List;


public class UsuarioInsertTest {

    /*@Autowired
    private UsuarioRepository usuarioRepository;*/

    UsuarioService usuarioService = new UsuarioService();

    @Test
    public void criarUsuario() {

        Usuario user = new Usuario();
        Curso curso = new Curso();
        Departamento departamento = new Departamento();

        departamento.setId(1L);
        departamento.setArea("Tecnlogia");
        departamento.setNome("Coordenação de Sistema de Informação");

        curso.setNome("Sistemas de Informação");
        curso.setArea("Tecnologia");
        curso.setTipo("Presencial");
        curso.setId(1L);

        List<Curso> cursos = new ArrayList<>();

        cursos.add(curso);

        user.setId(1L);
        user.setNome("Gabriel Moreira");
        user.setCpf(1011114500L);
        user.setAtivo(true);
        user.setCurso(cursos);
        user.setDepartamento(departamento);
        user.setCargo("");
        user.setLimiteLivros(10);
        user.setTipo("Aluno");
        user.setTelefone("83999151199");
        user.setEmail("teste@projetoalana.com");

        usuarioService.save(user);

    }

}

/**
 * EXEMPLO DE USUARIO A SER INSERIDO NO USUARIO
 *
 *
     {
        "nome": "Gabriel Moreira",
        "cpf" : 10115111111,
        "ativo" : true,
        "curso":[{ "id" : 1}],
        "departamento" : { "id" : 1},
        "cargo" : "Professor",
        "limiteLivros" : 10,
        "tipo" : "Aluno",
        "telefone" : "83999505050",
        "email" : "teste111@gmail.com"
     }
 *
 *
 */
