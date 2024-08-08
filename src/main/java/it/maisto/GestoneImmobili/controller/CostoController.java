package it.maisto.GestoneImmobili.controller;

import it.maisto.GestoneImmobili.modelRequest.CostoRequest;
import it.maisto.GestoneImmobili.modelResponse.CostoDto;
import it.maisto.GestoneImmobili.service.CostoService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
public class CostoController {
    @Autowired
    CostoService costoService;
    @PostMapping("/costo")
    public ResponseEntity<String> salvaCosto(@RequestBody CostoRequest costoRequest, @RequestParam int idImmobile, BindingResult bindingResult)throws BadRequestException {
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        costoService.salvaCosto(costoRequest,idImmobile);
        return ResponseEntity.ok("Costo aggiunto");
    }
    @GetMapping ("/costi")
    public List<CostoDto> costi (@RequestParam int idImmobile){
        return costoService.costoListDto(idImmobile);

    }
    @GetMapping("/totaleCosti")
    public  double totalecosti(@RequestParam int idImmobile){
        return costoService.totaleCostiPerImmobbile(idImmobile);
    }
    @GetMapping("/costiPerData")
    public List<CostoDto> costiPerData(@RequestParam int mese,@RequestParam int anno,@RequestParam int idImmobile){
        return costoService.costiPerData(mese,anno,idImmobile);
    }
}
