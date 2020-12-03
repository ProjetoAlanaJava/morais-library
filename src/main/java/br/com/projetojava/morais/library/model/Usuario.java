package br.com.projetojava.morais.library.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" })
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String matricula;

    private String password;

    private String authority;

    private String nome;

    private Long cpf;

    private Boolean ativo;

    @OneToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private String cargo;
    
    private Integer limiteLivros;

    private String telefone;

    private  String email;
}

/**
 *
 * [Depreciado]"ativo": true, ---> Não enviar mais, setado no AuthController /signup
 * [Depreciado]"limiteLivros": 10, ---> Não enviar, setado no AuthController /signup
    Request para criar novo usuario!
        {
         "matricula" : "20191022024",
         "password":  "123456",
         "authority" : "usuario", ---> usuario, externo, professor ou funcionario
         "nome": "Gabriel Moreira de Oliveira",
         "cpf": 12022014199,
         "curso": {"id": 3},
         "cargo": null, ---> Enviar cargo caso seja funcionario, Ex: Bibliotecario
         "telefone": "83999706080",
         "email":"teste@gmail.com"
        }
 **/
