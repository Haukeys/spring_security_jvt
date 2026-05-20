package it.itsacademy.spring_security_jvt.repository;


import it.itsacademy.spring_security_jvt.entity.Role;
import it.itsacademy.spring_security_jvt.entity.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Optional<Ruolo> findByRole(Role role);//QUERYNAME in questo caso rispetto alla documentazione non e ruolo name enum ma role enum
}
