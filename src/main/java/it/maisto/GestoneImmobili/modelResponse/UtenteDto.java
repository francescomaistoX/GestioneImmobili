package it.maisto.GestoneImmobili.modelResponse;

import lombok.Data;

@Data
public class UtenteDto {
    private int id ;
    private String nome;
    private String cognome;
    private String email;
    private String password;
}
