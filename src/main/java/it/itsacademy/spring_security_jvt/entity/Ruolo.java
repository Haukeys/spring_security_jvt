package it.itsacademy.spring_security_jvt.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Ruolo")
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Ruolo")
    private Long idRuolo;


    @Enumerated(EnumType.STRING) // Force le stockage du nom (USER) et non de l'index (1)
    @Column(nullable = false)
    private Role role;
}
