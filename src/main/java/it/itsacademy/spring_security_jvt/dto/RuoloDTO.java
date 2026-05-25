package it.itsacademy.spring_security_jvt.dto;

import it.itsacademy.spring_security_jvt.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuoloDTO {

    private Long idRuolo;
    @NotNull(message = "role non puo essere null")
    @NotBlank(message = "role non puo essere vuoto")
    private Role role;
}
