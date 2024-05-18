package it.maisto.GestoneImmobili.modelRequest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CostoRequest {
    private String nome;
    private String descizione;
    private double importo;
    private LocalDate data;
    private Integer idImmobbile;
}
