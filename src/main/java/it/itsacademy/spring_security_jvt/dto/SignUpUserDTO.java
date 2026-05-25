package it.itsacademy.spring_security_jvt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserDTO {

    @NotNull(message = "username non puo essere null")
    @NotBlank(message = "username non puo essere vuoto")
    private String username;
    @NotNull(message = "password non puo essere null")
    @NotBlank(message = "password non puo essere vuoto")
    private String password;
    @NotNull(message = "nome non puo essere null")
    @NotBlank(message = "nome non puo essere vuoto")
    private String nome;
    @NotNull(message = "cognome non puo essere null")
    @NotBlank(message = "cognome non puo essere vuoto")
    private String cognome;
}
