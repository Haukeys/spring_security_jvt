package it.itsacademy.spring_security_jvt.dto;

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
public class UtenteUpdateDTO {

    @NotNull(message = "nome non puo essere null")
    @NotBlank(message = "nome non puo essere vuoto")
    private String nome;
    @NotNull(message = "cognome non puo essere null")
    @NotBlank(message = "cognome non puo essere vuoto")
    private String cognome;
}
