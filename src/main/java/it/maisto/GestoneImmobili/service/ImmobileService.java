package it.maisto.GestoneImmobili.service;

import it.maisto.GestoneImmobili.repository.ImmobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImmobileService {
    @Autowired
    ImmobileRepository immobileRepo;

}
