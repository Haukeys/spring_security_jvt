package it.itsacademy.spring_security_jvt.service;



import it.itsacademy.spring_security_jvt.dto.LoginUserDTO;
import it.itsacademy.spring_security_jvt.dto.SignUpUserDTO;
import it.itsacademy.spring_security_jvt.dto.UtenteDTO;
import it.itsacademy.spring_security_jvt.entity.Ruolo;
import it.itsacademy.spring_security_jvt.entity.Utente;
import it.itsacademy.spring_security_jvt.entity.Role;
import it.itsacademy.spring_security_jvt.mapper.UtenteMapper;
import it.itsacademy.spring_security_jvt.repository.RuoloRepository;
import it.itsacademy.spring_security_jvt.repository.UtenteRepository;
import it.itsacademy.spring_security_jvt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor // genera i costrutorI con le proprietà 'final' per l' injection automatica
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UtenteRepository utenteRepository;
    @Autowired
    private final RuoloRepository ruoloRepository;
    @Autowired
    private final UtenteMapper utenteMapper;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UtenteDTO signUpUser(SignUpUserDTO signUpUserDTO) {
        // verificase l'username sta già in db
        if (utenteRepository.findByUsername(signUpUserDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Errore username già usato");
        }

        Utente utente = new Utente();

        // assegnazione del username
        utente.setUsername(signUpUserDTO.getUsername());

        //ENCRYPTION/CODIFICA : qua inizia l encryption/hashing della password grazie a Bcrypt
        String passwordHashed = passwordEncoder.encode(signUpUserDTO.getPassword());
        utente.setPassword(passwordHashed);

        Ruolo defaultRuolo = ruoloRepository.findByRole(Role.USER)
                .orElseThrow(() -> new RuntimeException("Errore il ruolo non esiste!"));

        // inizializzazione del set di ruolo
        Set<Ruolo> userRoles = new HashSet<>();
        userRoles.add(defaultRuolo);

        // assegnazione del set di ruolo a l'utente
        utente.setRuoli(userRoles);


        Utente saved = utenteRepository.save(utente);


        return utenteMapper.toUtenteDTO(saved);
    }

    @Override
    public UtenteDTO loginUser(LoginUserDTO loginUserDTO) {
        //Ricerca del uttente con il suo username se errore soleva un excption
        Utente utente = utenteRepository.findByUsername(loginUserDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("username inesistente o username non trovato"));

        //DECRYPT / DECODIFICA : BCrypt compare le mot de passe brut fourni avec le hash stocké en BDD
        // il metodo .matches() applica il SALE e l' algorithmo verifiva che tutto corrisponde
        if (!passwordEncoder.matches(loginUserDTO.getPassword(), utente.getPassword())) {
            throw new RuntimeException("password non valida: password incorrecto");
        }

        // Authentication riuscita.
        // (Nota : piu tardi, quando JWT sarà implementato, questo metodo ritornera una String che contine il tokone)
        return utenteMapper.toUtenteDTO(utente);
    }
}
