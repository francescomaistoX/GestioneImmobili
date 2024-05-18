package it.maisto.GestoneImmobili.modelRequest;

import lombok.Data;

@Data
public class UtenteRequest {
  private String nome;
  private String cognome;
  private String email;
  private String password;
}
