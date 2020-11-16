package br.com.projetojava.morais.library.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Table(name = "espacos")
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String setor;

    private Integer andar;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss", timezone = "Brazil/East")
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date horarioAbertura;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss", timezone = "Brazil/East")
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date horarioFechamento;

    private String tipo;

    private Integer capacidade;

}

/** Modelo de Espaco para enviar request
 * {
 *     "nome": "Tambau",
 *     "setor": "Setor A",
 *     "andar": 1,
 *     "horarioAbertura": "08:00:00", Horas:Minutos:Segundos
 *     "horarioFechamento": "18:00:00",
 *     "tipo": "Sala de Estudo",
 *     "capacidade" : 10
 * }
 */

