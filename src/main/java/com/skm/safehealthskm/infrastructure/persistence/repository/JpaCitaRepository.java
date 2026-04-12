package com.skm.safehealthskm.infrastructure.persistence.repository;

import com.skm.safehealthskm.domain.model.Cita;
import com.skm.safehealthskm.infrastructure.persistence.entity.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaCitaRepository extends JpaRepository<CitaEntity, Long> {
    boolean existsByMedicoIdAndFechaInicioBetween(Long medicoId, LocalDateTime inicio, LocalDateTime fin);

    @Query("""
    SELECT COUNT(c) > 0 FROM CitaEntity c 
    WHERE c.medico.id = :medicoId 
    AND c.estado <> com.skm.safehealthskm.domain.model.enums.EstadoCita.CANCELADA
    AND (c.fechaInicio < :fin AND c.fechaFin > :inicio)
    """)
    boolean existsOverlapping(@Param("medicoId") Long medicoId,
                              @Param("inicio") LocalDateTime inicio,
                              @Param("fin") LocalDateTime fin);
    List<CitaEntity> findCitaEntitiesByPacienteId(Long pacienteId);
    List<CitaEntity> findCitaEntitiesByMedicoId(Long medicoId);
}
