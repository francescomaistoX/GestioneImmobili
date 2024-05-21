package it.maisto.GestoneImmobili.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Costo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String descrizione;
    private double importo;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "idImmobbile")
    private Immobile immobbile;
}
