package it.itsacademy.spring_security_jvt.service;

import it.itsacademy.spring_security_jvt.dto.UtenteDTO;
import it.itsacademy.spring_security_jvt.entity.Utente;
import it.itsacademy.spring_security_jvt.mapper.UtenteMapper;
import it.itsacademy.spring_security_jvt.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor // gestisce automaticamente il costruttore per i campi private final
public class UtenteServiceImpl implements UtenteService {


    private final UtenteRepository utenteRepository;
    private final UtenteMapper utenteMapper;

    @Override
    public List<UtenteDTO> getAllUtenti() {//VIEW
            //crea una lista di entita
            List<Utente> utenti = utenteRepository.findAll();
            //converte in dto la lista
            return utenteMapper.toUtenteDTOList(utenti);
        }

    @Override
    public UtenteDTO getUtenteById(UUID idUtente) {//VIEW ONE
        // cerca l utente usando il suo UUID o solleva un exception c est non esiste
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new RuntimeException("inesistente,impossibile trovare l'utente con ID: " + idUtente));


        return utenteMapper.toUtenteDTO(utente);
    }

    @Override
    public UtenteDTO updateUtente(UUID idUtente, String nome, String cognome) {

        // verifica l esistenza de l'utente da modificare
        Utente utente = utenteRepository.findByIdUtente(idUtente)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + idUtente));

        // aggiorna i campi autorizzati//i altri campi gestiti in authservice
        utente.setNome(nome);
        utente.setCognome(cognome);

        // salva les modifiche in db via Hibernate
        Utente uptdatedUtente = utenteRepository.save(utente);

        // torna l oggeto aggiornato e lo converte in DTO
        return utenteMapper.toUtenteDTO(uptdatedUtente);
    }

    @Override
    public void disableUtente(UUID idUtente) {//soft delete
        Utente utente = utenteRepository.findByIdUtente(idUtente)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + idUtente));


        System.out.println("L'utente " + utente.getUsername() + " è stato disabilitato.");
        //utente disabilitato ma sempre presente in db
        utenteRepository.save(utente);
    }
}

