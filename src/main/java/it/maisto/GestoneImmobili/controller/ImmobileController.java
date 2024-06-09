package it.maisto.GestoneImmobili.controller;


import it.maisto.GestoneImmobili.modelResponse.ImmobileDto;
import it.maisto.GestoneImmobili.service.ImmobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImmobileController {
    @Autowired
    ImmobileService immobileService;
    @GetMapping("/immobili/affittati")
    public List<ImmobileDto> listaImmobiliConAffitto (@PathVariable int idUtente){
       return immobileService.immobiliConAffitti(idUtente);
    }
    @GetMapping("/immobili/liberi")
    public List<ImmobileDto> listaImmobiliSenzaAffitto (@PathVariable int idUtente){
        return immobileService.immobiliNoAffitti(idUtente);
    }


}
