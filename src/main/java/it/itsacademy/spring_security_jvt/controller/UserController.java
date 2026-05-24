package it.itsacademy.spring_security_jvt.controller;
import it.itsacademy.spring_security_jvt.dto.UtenteDTO;
import it.itsacademy.spring_security_jvt.service.UtenteService;
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

        // 1. Récupérer tous les utilisateurs (Accessible par n'importe quel utilisateur authentifié)
        @GetMapping
        public ResponseEntity<List<UtenteDTO>> getAllUtenti() {
            List<UtenteDTO> utenti = utenteService.getAllUtenti(); // Assure-toi que cette méthode existe dans ton service
            return ResponseEntity.ok(utenti);
        }

        // 2. Récupérer un utilisateur par son ID
        @GetMapping("/{id}")
        public ResponseEntity<UtenteDTO> getUtenteById(@PathVariable UUID idUtente) {
            UtenteDTO utente = utenteService.getUtenteById(idUtente);
            return ResponseEntity.ok(utente);
        }

        // 3. Supprimer un utilisateur (Exemple de restriction d'accès par rôle)
        // Seul un utilisateur avec le rôle 'ADMIN' pourra exécuter cette requête
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUtente(@PathVariable UUID idUtente) {
            utenteService.disableUtente(idUtente);
            return ResponseEntity.noContent().build();
        }
    }


