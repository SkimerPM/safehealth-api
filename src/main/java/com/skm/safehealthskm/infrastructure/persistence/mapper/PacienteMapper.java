package com.skm.safehealthskm.infrastructure.persistence.mapper;

import com.skm.safehealthskm.domain.model.Paciente;
import com.skm.safehealthskm.infrastructure.persistence.entity.PacienteEntity;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {
    public Paciente toDomain (PacienteEntity entity){
        if (entity == null) return null;
        return Paciente.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .nombre(entity.getNombre())
                .build();
    }
    public PacienteEntity toEntity(Paciente domain){
        if (domain == null) return null;
        return PacienteEntity.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .email(domain.getEmail())
                .build();
    }
}
