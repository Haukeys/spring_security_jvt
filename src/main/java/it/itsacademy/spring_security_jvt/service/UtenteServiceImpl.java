package it.itsacademy.spring_security_jvt.service;

import it.itsacademy.spring_security_jvt.dto.UtenteDTO;
import it.itsacademy.spring_security_jvt.entity.Utente;
import it.itsacademy.spring_security_jvt.mapper.UtenteMapper;
import it.itsacademy.spring_security_jvt.repository.UtenteRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private final UtenteRepository utenteRepository;

    @Autowired
    private final UtenteMapper utenteMapper;

    @Override
    public List<UtenteDTO> getAllUtenti() {//VIEW

        return utenteRepository.findAll().stream()
                // converte ogni entita JPA in un UtenteDTO via MapStruct
                .map(utenteMapper::toUtenteDTO)
                // colletta tutto in una liste finale di DTOs
                .collect(Collectors.toList());
    }

    @Override
    public UtenteDTO getUtenteById(UUID idUtente) {//VIEW ONE
        // cerca l utente usando il suo UUID o solleva un exception c est non esiste
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new RuntimeException("inesistente,impossibile trovare l'utente con ID: " + idUtente));


        return utenteMapper.toUtenteDTO(utente);
    }

    @Override
    public UtenteDTO updateUtente(UUID idUtente, UtenteDTO utenteDTO) {//UPDATE
        // verifica l esistenza de l'utente da modificare
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new RuntimeException("Impossible aggiornare: Utente inesistente"));

        // aggiorna i campi autorizzati
        utente.setUsername(utenteDTO.getUsername());
        utente.setRuoli(utenteDTO.getRuoli());

        // salva les modifiche in db via Hibernate
        Utente updatedUtente = utenteRepository.save(utente);

        // torna l oggeto aggiornato e lo converte in DTO
        return utenteMapper.toUtenteDTO(updatedUtente);
    }

    @Override
    public void deleteUtente(UUID idUtente) {//DELETE
        // verifica se l' id esiste per tentare la cancellazione
        if (!utenteRepository.existsById(idUtente)) {
            throw new RuntimeException("Impossible eliminare: utente inesistente");
        }
        // cancella l'utente corrispondente a l'UUID fornito
        utenteRepository.deleteById(idUtente);
    }
}
