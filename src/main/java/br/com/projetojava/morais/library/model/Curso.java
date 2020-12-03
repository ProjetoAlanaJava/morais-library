package br.com.projetojava.morais.library.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@ToString
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String area;

    private String tipo;

    @OneToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

}

/** Modelo de Request para criação de departamentos
 * {
 *     "nome": "Psicologia",
 *     "area": "Humanas",
 *     "tipo": "Presencial",
 *     "departamento": {"id": 1}
 * }
 **/


