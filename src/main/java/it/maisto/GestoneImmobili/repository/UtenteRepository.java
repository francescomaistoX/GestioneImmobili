package it.maisto.GestoneImmobili.repository;

import it.maisto.GestoneImmobili.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Integer> {
 public Optional<Utente> findByEmail(String email);
}
