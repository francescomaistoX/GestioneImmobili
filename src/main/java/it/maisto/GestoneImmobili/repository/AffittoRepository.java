package it.maisto.GestoneImmobili.repository;

import it.maisto.GestoneImmobili.model.Affitto;
import it.maisto.GestoneImmobili.model.Immobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface AffittoRepository extends JpaRepository<Affitto,Integer> {


    List<Affitto> findByImmobile(Immobile immobile);
}
