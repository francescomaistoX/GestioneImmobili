package it.maisto.GestoneImmobili;

import it.maisto.GestoneImmobili.model.AffittoScaduto;
import it.maisto.GestoneImmobili.model.Immobile;
import it.maisto.GestoneImmobili.repository.ImmobileRepository;
import it.maisto.GestoneImmobili.service.AffittoService;
import it.maisto.GestoneImmobili.service.ImmobileService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AffittoJob implements Job {
    @Autowired
    AffittoService affittoService;
    @Autowired
    ImmobileService immobileService;
    @Autowired
    ImmobileRepository immobileRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Immobile> immobili = immobileRepository.findAll();
        for(Immobile i : immobili){
            int idImmobile = i.getId();
            affittoService.rimuoviAffittiScaduti(idImmobile);
        }
    }
}
