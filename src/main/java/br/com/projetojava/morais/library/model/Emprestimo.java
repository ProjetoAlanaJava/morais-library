package br.com.projetojava.morais.library.model;

import br.com.projetojava.morais.library.model.books.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(of = {"id"})
@Data
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    Usuario usuario;

    @OneToOne
    Usuario funcionario;

    @OneToOne
    Livro livro;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "Brazil/East")
    @NotNull
    String dataEmprestimo;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss", timezone = "Brazil/East")
    @NotNull
    String horaEmprestimo;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "Brazil/East")
    @NotNull
    String dataDevolução;

    @Column(columnDefinition = "INT DEFAULT 0")
    Integer renovacoes;

}

/*
    {
        "usuario": {"id": 1, "matricula": "20191022024"},
        "funcionario" : {"id":2 , "matricula": "20251025321"},
        "livro" : {"id": 1}
    }
 */
