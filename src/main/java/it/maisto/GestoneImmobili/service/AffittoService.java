package it.maisto.GestoneImmobili.service;

import it.maisto.GestoneImmobili.model.Affitto;
import it.maisto.GestoneImmobili.model.Immobile;
import it.maisto.GestoneImmobili.modelRequest.AffittoRequest;
import it.maisto.GestoneImmobili.modelResponse.AffittoDto;
import it.maisto.GestoneImmobili.repository.AffittoRepository;
import it.maisto.GestoneImmobili.repository.ImmobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AffittoService {
    @Autowired
    AffittoRepository affittoRepo;
    @Autowired
    ImmobileRepository immobileRepo;

    public void saveAffitto (AffittoRequest affittoRequest,int idImmobile){
        Affitto affitto = new Affitto();
        affitto.setId(affittoRequest.getId());
        affitto.setInizio(affittoRequest.getInizio());
        affitto.setScadenza(affittoRequest.getScadenza());
        affitto.setNomeAffittuario(affittoRequest.getNomeAffittuario());
        affitto.setCognomerAffitttuario(affittoRequest.getCognomerAffitttuario());
        affitto.setNumeroCellulare(affittoRequest.getNumeroCellulare());

        affittoRepo.save(affitto);
    }
}
