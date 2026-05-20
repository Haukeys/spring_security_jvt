package it.itsacademy.spring_security_jvt.entity;
//jwt

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)//
    @Column(name = "id_Utente")
    private UUID idUtente;//UUID standard c est mieux
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name="ruoli_user",
            joinColumns = @JoinColumn(name = "id_Utente"), // Clé étrangère de l'entité actuelle (ex: User)
            inverseJoinColumns = @JoinColumn(name = "idRuolo")) // Clé étrangère de l'entité Ruolo)
    @Column(nullable = false)
    private Set<Ruolo>ruoli;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;

    public Boolean isAttivo(){
        return true;
    }
    public Boolean isDisabilitato(){
        return false;
    }

}
