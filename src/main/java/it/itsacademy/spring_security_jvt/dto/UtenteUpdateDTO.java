package it.itsacademy.spring_security_jvt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteUpdateDTO {


    private String nome;

    private String cognome;
}
