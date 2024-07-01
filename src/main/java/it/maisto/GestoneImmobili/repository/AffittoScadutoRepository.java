package it.maisto.GestoneImmobili.repository;

import it.maisto.GestoneImmobili.model.Affitto;
import it.maisto.GestoneImmobili.model.AffittoScaduto;
import it.maisto.GestoneImmobili.model.Immobile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AffittoScadutoRepository extends JpaRepository<AffittoScaduto,Integer> {

    List<AffittoScaduto> findByImmobbile(Immobile immobile);
}
