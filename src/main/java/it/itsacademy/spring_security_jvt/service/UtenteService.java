package it.itsacademy.spring_security_jvt.service;


import it.itsacademy.spring_security_jvt.dto.UtenteDTO;
import it.itsacademy.spring_security_jvt.entity.Role;

import java.util.List;
import java.util.UUID;

public interface UtenteService {

        // Recupera tutti i utenti
        public List<UtenteDTO> getAllUtenti();

        // recupera un utente usando l UUID
        public UtenteDTO getUtenteById(UUID idUtente);

        // aggiorna le informazione del Utente(quelle modificabili)
        public UtenteDTO updateUtente(UUID idUtente, String nome, String cognome);

        // disabilita l'utente
        public void disableUtente(UUID idUtente);

}
