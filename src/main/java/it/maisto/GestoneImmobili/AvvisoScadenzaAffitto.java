package it.maisto.GestoneImmobili;


import it.maisto.GestoneImmobili.model.Immobile;
import it.maisto.GestoneImmobili.repository.ImmobileRepository;
import it.maisto.GestoneImmobili.service.AffittoService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AvvisoScadenzaAffitto implements Job {


    @Autowired
    private AffittoService affittoService;
    @Autowired
    ImmobileRepository immobileRepository;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Immobile> immobili = immobileRepository.findAll();
        for(Immobile i : immobili){
            int idImmobile = i.getId();
            affittoService.avvisoAffittiScaduti(idImmobile);
        }
    }
}
