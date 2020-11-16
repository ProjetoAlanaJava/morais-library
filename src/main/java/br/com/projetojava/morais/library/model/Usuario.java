package br.com.projetojava.morais.library.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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
    private Curso curso; // FK

    private String cargo;

    @Column(nullable = false)
    private Integer limiteLivros;

    private String telefone;

    private  String email;
}

/**
    Request para criar novo usuario!

    {
        "matricula" : "20191022024",
        "password":  "123456",
        "authority" : "usuario", --> Pode ser, usuario(Aluno ou Externo), funcionario, professor
        "nome": "Gabriel Moreira de Oliveira",
        "cpf": 12022014199,
        "ativo": true,
        "curso": {"id": 1},
        [DEPRECIADO]"departamento": foi colocado diretamente no curso, não enviar mais
        "cargo": null, --> Apenas para Professor ou Funcionario
        [DEPRECIADO]"tipo": tipo foi substituido por authority, não enviar mais!
        "limiteLivros": 10,
        "telefone": "83999706080",
        "email":"teste@gmail.com"
    }
        Modelo sem os campos depreciados
        {
         "matricula" : "20191022024",
         "password":  "123456",
         "authority" : "usuario",
         "nome": "Gabriel Moreira de Oliveira",
         "cpf": 12022014199,
         "ativo": true,
         "curso": {"id": 3},
         "cargo": null,
         "limiteLivros": 10,
         "telefone": "83999706080",
         "email":"teste@gmail.com"
        }
 **/
