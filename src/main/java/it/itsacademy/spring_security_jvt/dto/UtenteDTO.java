package it.itsacademy.spring_security_jvt.dto;

import it.itsacademy.spring_security_jvt.entity.Ruolo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteDTO {

    private UUID idUtente;
    @NotNull(message = "username non puo essere null")
    @NotBlank(message = "username non puo essere vuoto")
    private String username;
    @NotNull(message = "la lista non puo essere null")
    @NotBlank(message = "la lista non puo essere vuoto")
    private Set<RuoloDTO> ruoli;
    @NotNull(message = "nome non puo essere null")
    @NotBlank(message = "nome non puo essere vuoto")
    private String nome;
    @NotNull(message = "cognome non puo essere null")
    @NotBlank(message = "cognome non puo essere vuoto")
    private String cognome;
}