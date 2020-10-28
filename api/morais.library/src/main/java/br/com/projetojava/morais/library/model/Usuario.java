package br.com.projetojava.morais.library.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" })
@ToString
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private Long cpf;

    private Boolean ativo;

    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = Curso.class)
    @JoinColumn(name = "curso_id")
    private List<Curso> curso; // FK

    @OneToOne(cascade = CascadeType.PERSIST, targetEntity = Departamento.class)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento; // FK

    private String cargo;

    private String tipo;

    @Column(nullable = false)
    private Integer limiteLivros;

    private String telefone;

    private  String email;

}
