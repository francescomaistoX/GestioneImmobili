package it.maisto.GestoneImmobili.modelResponse;

import lombok.Data;

@Data
public class LoginDto {
    String  accessToken;
    UtenteDto user ;
}
