package br.com.projetojava.morais.library.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "Brazil/East")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss", timezone = "Brazil/East")
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date horarioInicio;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss", timezone = "Brazil/East")
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date horarioTermino;

    private String status; // Agendado, Ocorrendo, Realizado, Cancelado
}

/** Exemplo de Request para evento
    {
        "title": "Inova UNIESP",
        "date": "20-10-2021",
        "horarioInicio": "08:00:00",
        "horarioTermino": "22:00:00",
        "status": "Angedado"
    }
**/