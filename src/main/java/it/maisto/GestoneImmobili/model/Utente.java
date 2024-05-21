package it.maisto.GestoneImmobili.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    @OneToMany(mappedBy = "utente",cascade = CascadeType.REMOVE)
    private List<Immobile> immobbili;


}
