package it.itsacademy.spring_security_jvt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserDTO {
    private String username;
    private String password;
    private String nome;
    private String cognome;
}
