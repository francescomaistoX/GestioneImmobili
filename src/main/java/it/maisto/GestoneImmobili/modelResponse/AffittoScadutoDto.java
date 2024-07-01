package it.maisto.GestoneImmobili.modelResponse;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AffittoScadutoDto {
    private int id;
    private LocalDate inizio;
    private LocalDate scadenza;
    private String nomeAffittuario;
    private String cognomerAffitttuario;
    private String numeroCellulare;
    private Integer idImmobbile;
}
