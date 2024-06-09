package it.maisto.GestoneImmobili.service;

import it.maisto.GestoneImmobili.exception.NotFoundException;
import it.maisto.GestoneImmobili.model.Costo;
import it.maisto.GestoneImmobili.model.Immobile;
import it.maisto.GestoneImmobili.modelRequest.CostoRequest;
import it.maisto.GestoneImmobili.modelResponse.CostoDto;
import it.maisto.GestoneImmobili.repository.CostoRepository;
import it.maisto.GestoneImmobili.repository.ImmobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostoService {
    @Autowired
    CostoRepository costoRepository;
    @Autowired
    ImmobileService immobileService;
 @Autowired
    ImmobileRepository immobileRepository;


 public CostoDto convertiDto(Costo costo){
     CostoDto costoDto= new CostoDto();
     costoDto.setData(costo.getDate());
     costoDto.setNome(costo.getNome());
     costoDto.setId(costo.getId());
     costoDto.setImporto(costo.getImporto());
     costoDto.setDescizione(costo.getDescrizione());
     costoDto.setIdImmobbile(costo.getImmobile().getId());
     return costoDto;
 }
 public CostoDto salvaCosto(CostoRequest costoRequest,int idImmobile){
     Immobile immobile = immobileService.trovaImmobilePerId(idImmobile);
     Costo costo = new Costo();
     costo.setDate(costoRequest.getData());
     costo.setImporto(costo.getImporto());
     costo.setNome(costoRequest.getNome());
     costo.setDescrizione(costoRequest.getDescizione());
     costo.setImmobile(immobile);
     costoRepository.save(costo);
     immobile.getCosti().add(costo);
     immobileRepository.save(immobile);
     return convertiDto(costo);
 }
    public Costo  trovaCostoPerId(int id){
        return costoRepository.findById(id).orElseThrow(() -> new NotFoundException("Annuncio con id=" + id + " non trovato"));
    }
    public CostoDto  trovaCostoDtoPerId(int id){
        Costo costo= costoRepository.findById(id).orElseThrow(() -> new NotFoundException("Annuncio con id=" + id + " non trovato"));
        return convertiDto(costo);

    }

    public double totaleCostiPerImmobbile(int idImmobile){
      Immobile immobile = immobileService.trovaImmobilePerId(idImmobile);
       return   costoRepository.findTotalCostByImmobile(immobile);
    }
    public List<Costo> costoList (int idImmobile){
        Immobile immobile = immobileService.trovaImmobilePerId(idImmobile);
        return costoRepository.findByImmobile(immobile);
    }
}
