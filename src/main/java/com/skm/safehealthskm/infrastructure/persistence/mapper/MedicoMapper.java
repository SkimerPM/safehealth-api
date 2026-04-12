package com.skm.safehealthskm.infrastructure.persistence.mapper;

import com.skm.safehealthskm.domain.model.Medico;
import com.skm.safehealthskm.infrastructure.persistence.entity.MedicoEntity;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {
    public Medico toDomain(MedicoEntity entity){
        if (entity == null) return null;
        return Medico.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .nombre(entity.getNombre())
                .especialidad(entity.getEspecialidad())
                .build();
    }

    public MedicoEntity toEntity(Medico domain){
        if(domain == null) return null;
        return MedicoEntity.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .nombre(domain.getNombre())
                .especialidad(domain.getEspecialidad())
                .build();
    }
}
