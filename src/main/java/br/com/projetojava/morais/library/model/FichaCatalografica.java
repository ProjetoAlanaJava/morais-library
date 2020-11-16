package br.com.projetojava.morais.library.model;

import br.com.projetojava.morais.library.model.books.Autor;
import br.com.projetojava.morais.library.model.books.Editora;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" })
@Data
@Entity
public class FichaCatalografica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String subtitulo;

    private String assuntos;

    @OneToOne
    private Autor autor;

    @Column(unique = true)
    private Long ISBN;

    @OneToOne
    private Editora editora;

    private String localPublicacao;

    private String dataPublicacao;

    private Integer numPaginas;

}
/** Modelo para request de Ficha Catalografica
    {
            "titulo" : "Desenvolvimento ambiental do pantanal Brasileiro",
            "subtitulo" : "Os impactos da grande expansao capitalista nas matas brasileiras",
            "autor": { "id": 1},
            "assuntos": "Trabalho de conclusão de curso(graduação),
            "isbn" : 8175257660,
            "editora": {"id": 1},
            "localPublicacao" : "Centro Universitario UNIESP - João Pessoa",
            "dataPublicacao" : "2020",
            "numPaginas" : 250,
            }
**/
