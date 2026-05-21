package it.itsacademy.spring_security_jvt.mapper;


import it.itsacademy.spring_security_jvt.dto.SignUpUserDTO;
import it.itsacademy.spring_security_jvt.dto.UtenteDTO;
import it.itsacademy.spring_security_jvt.entity.Utente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = RuoloMapper.class)
public interface UtenteMapper {

    @Mapping(target = "idUtente", ignore = true)
    @Mapping(target = "nome", ignore = true)
    @Mapping(target = "cognome", ignore = true)
    @Mapping(target = "ruoli", ignore = true)
    @Mapping(target ="isAttivo",ignore = true)

    public Utente toUtenteSignUp(SignUpUserDTO signUpUserDTO);


    public UtenteDTO toUtenteDTO(Utente utente);
    public Utente toUtente(UtenteDTO utenteDTO);

    public List<UtenteDTO> toUtenteDTOList(List<Utente> utenti);
}
