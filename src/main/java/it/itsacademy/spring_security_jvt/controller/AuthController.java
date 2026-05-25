package it.itsacademy.spring_security_jvt.controller;

import it.itsacademy.spring_security_jvt.dto.LoginUserDTO;
import it.itsacademy.spring_security_jvt.dto.SignUpUserDTO;
import it.itsacademy.spring_security_jvt.dto.UtenteDTO;
import it.itsacademy.spring_security_jvt.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService; // Injecte ton AuthService
    //on commence en no auth et apres on prend la clee
    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UtenteDTO> signup(@Valid @RequestBody SignUpUserDTO signupDto) {
        UtenteDTO createdUtente = authService.signUp(signupDto);
        return new ResponseEntity<>(createdUtente, HttpStatus.CREATED);
    }

    // C'est ici et uniquement ici qu'on récupère le jeton JWT
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginUserDTO loginDto) {
        String token = authService.login(loginDto);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}


