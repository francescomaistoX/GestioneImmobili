package it.maisto.GestoneImmobili.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Affitto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate inizio;
    private LocalDate scadenza;
    private String nomeAffittuario;
    private String cognomerAffitttuario;
    private String numeroCellulare;
    @ManyToOne
    @JoinColumn(name = "idImmobile")
    private Immobile immobile;
}
