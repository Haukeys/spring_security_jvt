package it.itsacademy.spring_security_jvt.controller;
import it.itsacademy.spring_security_jvt.dto.UtenteDTO;
import it.itsacademy.spring_security_jvt.dto.UtenteUpdateDTO;
import it.itsacademy.spring_security_jvt.service.UtenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

    @RestController
    @RequestMapping(path="/utenti")
    @RequiredArgsConstructor
    public class UserController {

        private final UtenteService utenteService; // Injecte ton service utilisateur

        // Récupérer tous les utilisateurs (Accessible par n'importe quel utilisateur authentifié)
        @GetMapping
        public ResponseEntity<List<UtenteDTO>> getAllUtenti() {
            List<UtenteDTO> utenti = utenteService.getAllUtenti(); // Assure-toi que cette méthode existe dans ton service
            return ResponseEntity.ok(utenti);
        }

        //Récupérer un utilisateur par son ID
        @GetMapping("/{id}")
        public ResponseEntity<UtenteDTO> getUtenteById(@Valid @PathVariable("id") UUID idUtente) {//ajout de "id pour faire comprendre a spring que c est l id de l utente qu on veux"
            UtenteDTO utente = utenteService.getUtenteById(idUtente);
            return ResponseEntity.ok(utente);
        }
        @PutMapping("/{id}")
        public ResponseEntity<UtenteDTO> updateUtente(@Valid @PathVariable("id") UUID idUtente, @RequestBody UtenteUpdateDTO utenteUpdateDTO) {

            UtenteDTO utenteUpdated = utenteService.updateUtente(idUtente, utenteUpdateDTO);
            return ResponseEntity.ok(utenteUpdated);
        }

        // Supprimer un utilisateur (Exemple de restriction d'accès par rôle)
        // Seul un utilisateur avec le rôle 'ADMIN' pourra exécuter cette requête
        @DeleteMapping("/{id}")
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<Void> deleteUtente(@Valid @PathVariable("id") UUID idUtente) {
            utenteService.disableUtente(idUtente);
            return ResponseEntity.noContent().build();
        }
    }


