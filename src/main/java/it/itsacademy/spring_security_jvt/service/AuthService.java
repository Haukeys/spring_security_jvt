package it.itsacademy.spring_security_jvt.service;

import it.itsacademy.spring_security_jvt.dto.LoginUserDTO;
import it.itsacademy.spring_security_jvt.dto.SignUpUserDTO;
import it.itsacademy.spring_security_jvt.dto.UtenteDTO;


public interface AuthService {
    UtenteDTO signUp(SignUpUserDTO signupDto);
    String login(LoginUserDTO loginDto);
}