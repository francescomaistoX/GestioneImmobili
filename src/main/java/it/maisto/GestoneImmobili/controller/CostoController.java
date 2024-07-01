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
    public ResponseEntity<String> salvaCosto(@RequestBody CostoRequest costoRequest, @RequestParam int idImmobbile, BindingResult bindingResult)throws BadRequestException {
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        costoService.salvaCosto(costoRequest,idImmobbile);
        return ResponseEntity.ok("Costo aggiunto");
    }
    @PostMapping("/costi")
    public List<CostoDto> costi (@RequestParam int idImmobbile){
        return costoService.costoListDto(idImmobbile);

    }
    @GetMapping("/totaleCosti")
    public  double totalecosti(@RequestParam int idImmobbile){
        return costoService.totaleCostiPerImmobbile(idImmobbile);
    }
}
