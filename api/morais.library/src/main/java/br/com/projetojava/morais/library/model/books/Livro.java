package br.com.projetojava.morais.library.model.books;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @OneToMany
    @JoinColumn(name = "autor_id")
    private List<Autor> autores;

    private String assunto;

    private Integer qtd_geral;

    private Integer ano_publicacao;

    @OneToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;

    @OneToOne
    private Tipo tipo;

    @OneToOne
    private Categoria categoria;

    @Column(unique = true)
    private String isbn;

}


