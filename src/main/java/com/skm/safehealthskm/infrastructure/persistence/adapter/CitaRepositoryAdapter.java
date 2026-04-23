package com.skm.safehealthskm.infrastructure.persistence.adapter;

import com.skm.safehealthskm.domain.model.Cita;
import com.skm.safehealthskm.domain.repository.CitaRepository;
import com.skm.safehealthskm.infrastructure.persistence.entity.CitaEntity;
import com.skm.safehealthskm.infrastructure.persistence.mapper.CitaMapper;
import com.skm.safehealthskm.infrastructure.persistence.repository.JpaCitaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CitaRepositoryAdapter implements CitaRepository {

    private final JpaCitaRepository jpa;
    private final CitaMapper citaMapper;
    @Override
    public Cita guardar(Cita cita) {

        CitaEntity entity = citaMapper.toEntity(cita);

        CitaEntity guardada = jpa.save(entity);

        return citaMapper.toDomain(guardada);
    }

    @Override
    public boolean existeCitaEnRango(Long medicoId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return jpa.existsOverlapping(medicoId, fechaInicio, fechaFin);
    }

    @Override
    public boolean existeCitaEnRangoExcluyendoId(Long medicoId, Long citaId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return jpa.existsOverlappingExcluyendoId(
                medicoId,
                citaId,
                fechaInicio,
                fechaFin
        );
    }

    @Override
    public List<Cita> buscarPorPacienteId(Long pacienteId) {
        return jpa.findCitaEntitiesByPacienteId(pacienteId).stream().map(citaMapper::toDomain).toList();
    }

    @Override
    public List<Cita> buscarPorMedicoId(Long medicoId) {
        return jpa.findCitaEntitiesByMedicoId(medicoId).stream().map(citaMapper::toDomain).toList();
    }

    @Override
    public java.util.Optional<Cita> obtenerPorId(Long id) {
        return jpa.findById(id).map(citaMapper::toDomain);
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }
}
