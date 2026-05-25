package it.itsacademy.spring_security_jvt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO {

    @NotNull(message = "username non puo essere null")
    @NotBlank(message = "username non puo essere vuoto")
    private String username;

    @NotNull(message = "password non puo essere null")
    @NotBlank(message = "password non puo essere vuoto")
    private String password;
}
