package it.maisto.GestoneImmobili.controller;


import it.maisto.GestoneImmobili.modelRequest.ImmobileRequest;
import it.maisto.GestoneImmobili.modelResponse.ImmobileDto;
import it.maisto.GestoneImmobili.service.ImmobileService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImmobileController {
    @Autowired
    ImmobileService immobileService;
    @GetMapping("/immobili/affittati")
    public List<ImmobileDto> listaImmobiliConAffitto (@RequestParam int idUtente){
       return immobileService.immobiliConAffitti(idUtente);
    }
    @GetMapping("/immobili/liberi")
    public List<ImmobileDto> listaImmobiliSenzaAffitto (@RequestParam int idUtente){
        return immobileService.immobiliNoAffitti(idUtente);
    }
    @PostMapping("/immobile")
    public ResponseEntity<String> saveImmobile (@RequestBody  ImmobileRequest immobileRequest, @RequestParam("idUtente") int idUtente, BindingResult bindingResult) throws BadRequestException {
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        immobileService.saveImmobile(immobileRequest,idUtente);
        return ResponseEntity.ok("Immobbile salvato");
    }


}
