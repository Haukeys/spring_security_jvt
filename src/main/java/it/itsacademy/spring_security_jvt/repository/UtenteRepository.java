package it.itsacademy.spring_security_jvt.repository;

import it.itsacademy.spring_security_jvt.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtenteRepository extends JpaRepository<Utente, UUID> {
    Optional<Utente> findByUsername(String username);//QUERYNAME
    Optional<Utente> findByIdUtente(UUID idUtente);//QUERYNAME
}
