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



}
