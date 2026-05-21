package it.itsacademy.spring_security_jvt.dto;

import it.itsacademy.spring_security_jvt.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuoloDTO {

    private Long idRuolo;

    private Role role;
}
