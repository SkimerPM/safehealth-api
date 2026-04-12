package com.skm.safehealthskm.infrastructure.persistence.mapper;

import com.skm.safehealthskm.domain.model.Cita;
import com.skm.safehealthskm.infrastructure.persistence.entity.CitaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitaMapper {

    private final PacienteMapper pacienteMapper;
    private final MedicoMapper medicoMapper;

    public Cita toDomain(CitaEntity entity) {
        if (entity == null) return null;
        return Cita.builder()
                .id(entity.getId())
                .paciente(pacienteMapper
                        .toDomain(entity
                                .getPaciente()))
                .medico(medicoMapper.toDomain(entity.getMedico()))
                .fechaInicio(entity.getFechaInicio())
                .tipo(entity.getTipo())
                .estado(entity.getEstado())
                .build();
    }

    public CitaEntity toEntity(Cita domain) {
        if (domain == null) return null;
        return CitaEntity.builder().id(domain.getId()).paciente(pacienteMapper.toEntity(domain.getPaciente())).medico(medicoMapper.toEntity(domain.getMedico())).fechaInicio(domain.getFechaInicio()).fechaFin(domain.getFechaFin()).tipo(domain.getTipo()).estado(domain.getEstado()).build();
    }

}
