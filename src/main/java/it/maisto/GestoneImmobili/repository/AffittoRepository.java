package it.maisto.GestoneImmobili.repository;

import it.maisto.GestoneImmobili.model.Affitto;
import it.maisto.GestoneImmobili.model.Immobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AffittoRepository extends JpaRepository<Affitto,Integer> {


    List<Affitto> findByImmobile(Immobile immobile);


    @Query("SELECT a FROM Affitto a WHERE a.scadenza = :scadenza AND a.immobile = :immobile")
    List<Affitto> findByScadenza(LocalDate scadenza, Immobile immobile);
}
