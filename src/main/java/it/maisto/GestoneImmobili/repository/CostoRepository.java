package it.maisto.GestoneImmobili.repository;

import it.maisto.GestoneImmobili.model.Costo;
import it.maisto.GestoneImmobili.model.Immobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostoRepository extends JpaRepository<Costo,Integer> {

    List<Costo> findByImmobile(Immobile immobile);
    @Query("SELECT SUM(importo) FROM Costo c WHERE c.immobile = :immobile")
    Double findTotalCostByImmobile(@Param("immobile") Immobile immobile);
    @Query("SELECT c FROM Costo c WHERE (MONTH(c.date) = :mese OR YEAR(c.date) = :anno) AND c.immobile.id = :immobileId")
    List<Costo> findByMeseAnno(@Param("mese") int mese, @Param("anno") int anno, @Param("immobileId") int immobileId);

}
