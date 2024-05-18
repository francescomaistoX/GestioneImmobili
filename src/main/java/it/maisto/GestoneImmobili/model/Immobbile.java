package it.maisto.GestoneImmobili.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Immobbile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int               id;
    private String            nome;
    private String            indirizzo;
    @OneToMany(mappedBy = "immobbile",cascade = CascadeType.REMOVE)
    private List<Affitto>     affitto;
    @OneToMany(mappedBy = "immobbile",cascade = CascadeType.REMOVE)
    private List<Costo>       costi;
    @ManyToOne
    @JoinColumn(name = "idUtente")
    private Utente            utente;
}
