package it.itsacademy.spring_security_jvt.mapper;


import it.itsacademy.spring_security_jvt.dto.RuoloDTO;
import it.itsacademy.spring_security_jvt.entity.Ruolo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RuoloMapper {

    public RuoloDTO toRuoloDTO(Ruolo ruolo);

    public Ruolo toRuoloDTO(RuoloDTO ruoloDTO);

}
