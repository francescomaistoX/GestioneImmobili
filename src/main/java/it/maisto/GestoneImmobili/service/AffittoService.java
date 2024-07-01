package it.maisto.GestoneImmobili.service;

import it.maisto.GestoneImmobili.model.Affitto;
import it.maisto.GestoneImmobili.model.AffittoScaduto;
import it.maisto.GestoneImmobili.model.Immobile;
import it.maisto.GestoneImmobili.modelRequest.AffittoRequest;
import it.maisto.GestoneImmobili.modelResponse.AffittoDto;
import it.maisto.GestoneImmobili.repository.AffittoRepository;
import it.maisto.GestoneImmobili.repository.AffittoScadutoRepository;
import it.maisto.GestoneImmobili.repository.ImmobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AffittoService {
    @Autowired
    AffittoRepository affittoRepo;
    @Autowired
    ImmobileService immobileService;
    @Autowired
    ImmobileRepository immobileRepository;
    @Autowired
    AffittoScadutoRepository affittoScadutoRepository;

   public List<Affitto> listaAffitti(int idImmobbile){
       Immobile immobile = immobileService.trovaImmobilePerId(idImmobbile);
       return affittoRepo.findByImmobile(immobile);
   }
   public AffittoDto convertiDto(Affitto affitto){
       AffittoDto affittoDto = new AffittoDto();
       affittoDto.setId(affitto.getId());
       affittoDto.setIdImmobbile(affitto.getImmobile().getId());
       affittoDto.setInizio(affitto.getInizio());
       affittoDto.setScadenza(affitto.getScadenza());
       affittoDto.setNomeAffittuario(affitto.getNomeAffittuario());
       affittoDto.setCognomerAffitttuario(affitto.getCognomerAffitttuario());
       affittoDto.setNumeroCellulare(affitto.getNumeroCellulare());
       return affittoDto;
   }
   public List<AffittoDto> listaAffittiDto(int idImmobbile){
       Immobile immobile = immobileService.trovaImmobilePerId(idImmobbile);
       List<AffittoDto> affittiDto = new ArrayList<>();
       List<Affitto> affitti = affittoRepo.findByImmobile(immobile);
       for(Affitto affitto : affitti){
           affittiDto.add( convertiDto(affitto));
       }
       return affittiDto;

   }
 public boolean saveAffitto (AffittoRequest affittoRequest , int idImmobbile){
      List<Affitto> affitti = listaAffitti(idImmobbile);
      for (Affitto affitto : affitti){
          if (!(affittoRequest.getScadenza().isBefore(affitto.getInizio())||affittoRequest.getInizio().isAfter(affitto.getScadenza()))){
              return false;
          }

      }
     Immobile immobile = immobileService.trovaImmobilePerId(idImmobbile);
     Affitto newAffitto = new Affitto();
     newAffitto.setImmobile(immobile);
     newAffitto.setInizio(affittoRequest.getScadenza());
     newAffitto.setScadenza(affittoRequest.getScadenza());
     newAffitto.setNomeAffittuario(affittoRequest.getNomeAffittuario());
     newAffitto.setCognomerAffitttuario(affittoRequest.getCognomerAffitttuario());
     newAffitto.setNumeroCellulare(affittoRequest.getNumeroCellulare());
     affittoRepo.save(newAffitto);
     immobile.getAffitti().add(newAffitto);
     immobileRepository.save(immobile);
     return true;
 }

    public void rimuoviAffittiScaduti(int immobileId) {
       Immobile immobile= immobileService.trovaImmobilePerId(immobileId);
        List<Affitto> affitti = affittoRepo.findByScadenza(LocalDate.now(), immobile);
        for(Affitto a : affitti){
            AffittoScaduto affittoScaduto= new AffittoScaduto();
            affittoScaduto.setCognomerAffitttuario(a.getCognomerAffitttuario());
            affittoScaduto.setNomeAffittuario(a.getNomeAffittuario());
            affittoScaduto.setNumeroCellulare(a.getNumeroCellulare());
            affittoScaduto.setInizio(a.getInizio());
            affittoScaduto.setScadenza(a.getScadenza());
            affittoScaduto.setImmobile(a.getImmobile());
            affittoScadutoRepository.save(affittoScaduto);
        }
        affittoRepo.deleteAll(affitti);
    }


}
