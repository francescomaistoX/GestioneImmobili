package it.maisto.GestoneImmobili.controller;

import it.maisto.GestoneImmobili.model.Immobile;
import it.maisto.GestoneImmobili.modelRequest.AffittoRequest;
import it.maisto.GestoneImmobili.modelResponse.AffittoDto;
import it.maisto.GestoneImmobili.modelResponse.AffittoScadutoDto;
import it.maisto.GestoneImmobili.service.AffittoScadutoService;
import it.maisto.GestoneImmobili.service.AffittoService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@RestController
public class AffittoController {

    @Autowired
    private AffittoService affittoService;
    @Autowired
    private AffittoScadutoService affittoScadutoService;

    @PostMapping("/controlla-scadenze")
    public ResponseEntity<String> controllaScadenze(@RequestParam int idImmobile) {
        affittoService.rimuoviAffittiScaduti(idImmobile);
        return ResponseEntity.ok("Controllo delle scadenze eseguito con successo.");
    }
    @PostMapping("/affitto")
    public ResponseEntity<String> salvaAffitto(@RequestBody AffittoRequest affittoRequest, @RequestParam("idImmobile") int idImmobile, BindingResult bindingResult)throws BadRequestException {
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        affittoService.saveAffitto(affittoRequest,idImmobile);
        return ResponseEntity.ok("Affitto Salvato");
    }
    @GetMapping("/affitti")
   public List<AffittoDto> affittiDto(@RequestParam int idImmobbile){
       return affittoService.listaAffittiDto(idImmobbile);
   }
   @GetMapping("/affittiScaduti")
   public List<AffittoScadutoDto> affittiScaduti(@RequestParam int idImmobiler){
        return affittoScadutoService.affittiScadutiDto(idImmobiler);
   }
}
