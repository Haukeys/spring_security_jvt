package it.itsacademy.spring_security_jvt.dto;

import it.itsacademy.spring_security_jvt.entity.Ruolo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteDTO {

    private UUID idUtente;//UUID standard c est mieux

    private String username;

    private String password;

    private Set<Ruolo> ruels;


    private String nome;

    private String cogonme;
}