package it.maisto.GestoneImmobili.service;

import it.maisto.GestoneImmobili.exception.NotFoundException;
import it.maisto.GestoneImmobili.model.Utente;
import it.maisto.GestoneImmobili.modelRequest.UtenteRequest;
import it.maisto.GestoneImmobili.modelResponse.UtenteDto;
import it.maisto.GestoneImmobili.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepo;

    public Utente save(UtenteRequest utenteRequest) {
        Utente utente= new Utente();
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setEmail(utenteRequest.getEmail());
        utente.setPassword(utenteRequest.getPassword());
        return utenteRepo.save(utente);
    }
    public Utente trovaUtentePerId(int id){
        return utenteRepo.findById(id).orElseThrow(() -> new NotFoundException("Annuncio con id=" + id + " non trovato"));
    }
    public Utente trovaUtenteByEmail(String email){
    return utenteRepo.findByEmail(email).orElseThrow(()->new NotFoundException("Utente non trovato"));
   }

}
