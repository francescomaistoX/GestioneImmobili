package it.maisto.GestoneImmobili.service;

import it.maisto.GestoneImmobili.model.Affitto;
import it.maisto.GestoneImmobili.model.AffittoScaduto;
import it.maisto.GestoneImmobili.model.Immobile;
import it.maisto.GestoneImmobili.modelResponse.AffittoDto;
import it.maisto.GestoneImmobili.modelResponse.AffittoScadutoDto;
import it.maisto.GestoneImmobili.repository.AffittoScadutoRepository;
import it.maisto.GestoneImmobili.repository.ImmobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AffittoScadutoService {
    @Autowired
    ImmobileService immobileService;
    @Autowired
    ImmobileRepository immobileRepository;
    @Autowired
    AffittoScadutoRepository affittoScadutoRepository;


    public AffittoScadutoDto convertiDto(AffittoScaduto affittoScaduto){
        AffittoScadutoDto affittoScadutoDto = new AffittoScadutoDto();
        affittoScadutoDto.setId(affittoScaduto.getId());
        affittoScadutoDto.setIdImmobbile(affittoScaduto.getImmobile().getId());
        affittoScadutoDto.setInizio(affittoScaduto.getInizio());
        affittoScadutoDto.setScadenza(affittoScaduto.getScadenza());
        affittoScadutoDto.setNomeAffittuario(affittoScaduto.getNomeAffittuario());
        affittoScadutoDto.setCognomerAffitttuario(affittoScaduto.getCognomerAffitttuario());
        affittoScadutoDto.setNumeroCellulare(affittoScaduto.getNumeroCellulare());
        return  affittoScadutoDto;
    }
    public List<AffittoScaduto> listaAffittiScaduti (int idImmobbile){
        Immobile immobile = immobileService.trovaImmobilePerId(idImmobbile);
       List<AffittoScaduto> affittiScaduti= affittoScadutoRepository.findByImmobile(immobile);
       return affittiScaduti;
    }
    public List<AffittoScadutoDto> affittiScadutiDto(int idImmobile){
        List<AffittoScadutoDto> affittiScadutiDto = new ArrayList<>();
        List<AffittoScaduto> affittiScaduti = listaAffittiScaduti(idImmobile);
        for(AffittoScaduto a : affittiScaduti){
            AffittoScadutoDto affittoScadutoDto = convertiDto(a);
            affittiScadutiDto.add(affittoScadutoDto);
        }
         return affittiScadutiDto;
    }
}
