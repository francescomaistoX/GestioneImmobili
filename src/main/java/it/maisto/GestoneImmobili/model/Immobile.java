package it.maisto.GestoneImmobili.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Immobile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int               id;
    private String            nome;
    private String            indirizzo;
    private String            immaggine;
    @OneToMany(mappedBy = "immobbile",cascade = CascadeType.REMOVE)
    private List<Affitto>     affitti;
    @OneToMany(mappedBy = "immobbile",cascade = CascadeType.REMOVE)
    private List<Costo>       costi;
    @ManyToOne
    @JoinColumn(name = "idUtente")
    private Utente            utente;
}
