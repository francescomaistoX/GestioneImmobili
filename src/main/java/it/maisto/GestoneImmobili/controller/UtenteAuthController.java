package it.maisto.GestoneImmobili.controller;

import it.maisto.GestoneImmobili.exception.LoginFaultException;
import it.maisto.GestoneImmobili.model.Utente;
import it.maisto.GestoneImmobili.modelRequest.LoginRequest;
import it.maisto.GestoneImmobili.modelRequest.UtenteRequest;
import it.maisto.GestoneImmobili.modelResponse.UtenteDto;
import it.maisto.GestoneImmobili.service.UtenteService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UtenteAuthController {
@Autowired
    UtenteService utenteService;

@PostMapping("/utente/register")
public ResponseEntity<String> register(@RequestBody UtenteRequest utenteRequest, BindingResult bindingResult) throws BadRequestException {
    if(bindingResult.hasErrors()){
        throw new BadRequestException(bindingResult.getAllErrors().toString());
    }
     utenteService.save(utenteRequest);
        return ResponseEntity.ok("Registrazione avvenuta");
}
    @PostMapping("/utente/login")
    public UtenteDto login(@RequestBody LoginRequest loginRequest, BindingResult bindingResult) throws BadRequestException {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
         Utente utente = utenteService.trovaUtenteByEmail(loginRequest.getEmail());
        UtenteDto utenteDto=utenteService.contertiDto(utente);
        if (Objects.equals(utenteDto.getPassword(), loginRequest.getPassword())){
            return utenteDto;
        }else {
              throw new LoginFaultException("username/password errate");
        }
}
}
