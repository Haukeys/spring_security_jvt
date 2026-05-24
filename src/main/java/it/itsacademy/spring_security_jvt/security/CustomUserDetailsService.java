package it.itsacademy.spring_security_jvt.security;


import it.itsacademy.spring_security_jvt.entity.Utente;
import it.itsacademy.spring_security_jvt.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Utente utente = utenteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con username: " + username));


        return new User(
                utente.getUsername(),//chiave identificativa

                //hashed da BCrypt nel db
                utente.getPassword(),

                //se false spring solevera un DisabledException.
                utente.getIsAttivo(),

                true,//accountNonExpired

                true,//credentialsNonExpired
                !utente.getIsAttivo(),//accountNonLocked
                getAuthorities(utente)
        );
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Utente utente) {
        // Vérification de sécurité : si la liste est null, on retourne une liste vide
        if (utente.getRuoli() == null) {
            return Collections.emptyList();
        }

        String[] authorities = utente.getRuoli().stream()
                // Optionnel : Ajoute le préfixe "ROLE_" si ce n'est pas déjà le cas,
                // c'est une convention forte dans Spring Security
                .map(item -> "ROLE_" + item.getRole().name())
                .toArray(String[]::new);

        return AuthorityUtils.createAuthorityList(authorities);
    }

}
