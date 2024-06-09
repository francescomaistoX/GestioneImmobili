package it.maisto.GestoneImmobili.repository;

import it.maisto.GestoneImmobili.model.Immobile;
import it.maisto.GestoneImmobili.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImmobileRepository extends JpaRepository<Immobile,Integer> {

    @Query("SELECT i FROM Immobile i WHERE i.affitti IS EMPTY")
    List<Immobile> findImmobiliSenzaAffitti(Utente utente);
    @Query("SELECT i FROM Immobile i WHERE i.affitti IS NOT EMPTY")
    List<Immobile> findImmobiliConAffitti( Utente utente);


}
