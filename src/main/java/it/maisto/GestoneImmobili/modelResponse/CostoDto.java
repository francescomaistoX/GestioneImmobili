package it.maisto.GestoneImmobili.modelResponse;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CostoDto {
    private String nome;
    private String descizione;
    private double importo;
    private LocalDate data;
    private Integer idImmobbile;
}
