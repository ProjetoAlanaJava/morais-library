package br.com.projetojava.morais.library.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"id"})
public class ReservaEspaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Evento evento;

    @OneToOne
    private Espaco espaco;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "Brazil/East")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date data;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss", timezone = "Brazil/East")
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date horarioInicioReserva;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss", timezone = "Brazil/East")
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date horarioFimReserva;

}
/** Modelo de Request Para Reserva de Espaco
    {
        "usuario" : {"id": 6},
        "evento" : null,
        "espaco" : {"id": 2},
        "data" : "20-12-2020",
        "horarioInicioReserva" : "18:00:00",
        "horarioFimReserva": "21:30:00"
    }
**/