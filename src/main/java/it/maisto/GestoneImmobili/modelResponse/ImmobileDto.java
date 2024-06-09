package it.maisto.GestoneImmobili.modelResponse;

import lombok.Data;

@Data
public class ImmobileDto {
    private int id;
    private String nome;
    private String immaggine;
    private String indirizzo;
    private Integer idUtente;
}
