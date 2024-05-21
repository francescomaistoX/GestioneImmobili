package it.maisto.GestoneImmobili.modelResponse;

import lombok.Data;

@Data
public class ImmobileDto {
    private String nome;
    private String immaggine;
    private String indirizzo;
    private Integer idUtente;
}
