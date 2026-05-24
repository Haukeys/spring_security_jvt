package it.itsacademy.spring_security_jvt.service;

import it.itsacademy.spring_security_jvt.dto.LoginUserDTO;
import it.itsacademy.spring_security_jvt.dto.SignUpUserDTO;
import it.itsacademy.spring_security_jvt.dto.UtenteDTO;
import it.itsacademy.spring_security_jvt.entity.Role;
import it.itsacademy.spring_security_jvt.entity.Ruolo;
import it.itsacademy.spring_security_jvt.entity.Utente;
import it.itsacademy.spring_security_jvt.mapper.UtenteMapper;
import it.itsacademy.spring_security_jvt.repository.RuoloRepository;
import it.itsacademy.spring_security_jvt.repository.UtenteRepository;
import it.itsacademy.spring_security_jvt.utility.JWTServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder; // Injecte le BCryptPasswordEncoder de ton ApplicationConfiguration
    private final AuthenticationManager authenticationManager; // Injecté depuis ApplicationConfiguration
    private final UserDetailsService userDetailsService; // Ton CustomUtenteDetailsService
    private final JWTServiceImpl jwtService; // Ton interface de gestion JWT
    private final RuoloRepository ruoloRepository;
    private final UtenteMapper utenteMapper;
    private final PasswordEncoder encoder;


    @Override
    public UtenteDTO signUp(SignUpUserDTO signupDto) {
// Check existence
        if (utenteRepository.findByUsername(signupDto.getUsername()).isPresent()) {
            throw new RuntimeException("Errore username già usato");
        }

        // Mapping
        Utente newUtente = utenteMapper.toUtenteSignUp(signupDto);
        newUtente.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        newUtente.setIsAttivo(true);

        // Gestion dynamique du rôle (l'astuce pour éviter les erreurs 403)
        Ruolo userRole = ruoloRepository.findByRole(Role.USER)
                .orElseGet(() -> {
                    Ruolo newRole = new Ruolo();
                    newRole.setRole(Role.USER);
                    return ruoloRepository.save(newRole);
                });

        newUtente.setRuoli(Set.of(userRole));


        return utenteMapper.toUtenteDTO(utenteRepository.save(newUtente));
    }

    @Override
    public String login(LoginUserDTO loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        return jwtService.generateToken(userDetails);
    }
}



