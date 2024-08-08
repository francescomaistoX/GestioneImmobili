package it.maisto.GestoneImmobili.service;

import it.maisto.GestoneImmobili.exception.BadRequestException;
import it.maisto.GestoneImmobili.exception.ErrorResponse;
import it.maisto.GestoneImmobili.exception.NotFoundException;
import it.maisto.GestoneImmobili.model.Immobile;
import it.maisto.GestoneImmobili.model.Utente;
import it.maisto.GestoneImmobili.modelRequest.ImmobileRequest;
import it.maisto.GestoneImmobili.modelResponse.ImmobileDto;
import it.maisto.GestoneImmobili.repository.ImmobileRepository;
import it.maisto.GestoneImmobili.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImmobileService {
    @Autowired
    ImmobileRepository immobileRepo;
    @Autowired
    UtenteService utenteServ;
    @Autowired
    UtenteRepository utenteRepository;



    public ImmobileDto conversioneDto(Immobile immobile){
        ImmobileDto immobileDto= new ImmobileDto();
        immobileDto.setId(immobile.getId());
        immobileDto.setNome(immobile.getNome());
        immobileDto.setIndirizzo(immobile.getIndirizzo());
        immobileDto.setImmaggine(immobile.getImmaggine());
        immobileDto.setIdUtente(immobile.getUtente().getId());
        return immobileDto;
    }

    public ImmobileDto saveImmobile(ImmobileRequest immobileRequest,int idUtente){
        try {
        Utente utente= utenteServ.trovaUtentePerId(idUtente);
        Immobile immobile= new Immobile();
        immobile.setNome(immobileRequest.getNome());
        immobile.setIndirizzo(immobileRequest.getIndirizzo());
        immobile.setImmaggine(immobileRequest.getImmaggine());
        immobile.setUtente(utente);
        immobileRepo.save(immobile);
        utente.getImmobbili().add(immobile);
        utenteRepository.save(utente);
         return conversioneDto(immobile);

        }catch (BadRequestException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    public List<ImmobileDto> immobiliNoAffitti(int idUtente){
        Utente utente = utenteServ.trovaUtentePerId(idUtente);
        List<Immobile> immobili= immobileRepo.findImmobiliSenzaAffitti(utente);
        List<ImmobileDto> immobiliDto = new ArrayList<>();
        for (Immobile i : immobili){
            ImmobileDto dto = new ImmobileDto();
            dto.setId(i.getId()
            );
            dto.setIdUtente(i.getUtente().getId());
            dto.setImmaggine(i.getImmaggine());
            dto.setIndirizzo(i.getIndirizzo());
            dto.setNome(i.getNome());
            immobiliDto.add(dto);
        }
        return immobiliDto;
    }
    public List<ImmobileDto> immobiliConAffitti(int idUtente){
        Utente utente = utenteServ.trovaUtentePerId(idUtente);
        List<Immobile> immobili= immobileRepo.findImmobiliConAffitti(utente);

        List<ImmobileDto> immobiliDto = new ArrayList<>();
        for (Immobile i : immobili){
            ImmobileDto dto = new ImmobileDto();
            dto.setId(i.getId());
            dto.setIdUtente(i.getUtente().getId());
            dto.setImmaggine(i.getImmaggine());
            dto.setIndirizzo(i.getIndirizzo());
            dto.setNome(i.getNome());
            immobiliDto.add(dto);
        }
        return immobiliDto;
    }
    public Immobile  trovaImmobilePerId(int id){
        return immobileRepo.findById(id).orElseThrow(() -> new NotFoundException("Immobile con id=" + id + " non trovato"));
    }
    public void cancellaImmobile(int id){
        Immobile immobile=trovaImmobilePerId(id);
        immobileRepo.delete(immobile);
    }

}
