package com.skm.safehealthskm.domain.repository;

import com.skm.safehealthskm.domain.model.Cita;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository {
    Cita guardar(Cita cita);
    boolean existeCitaEnRango(Long medicoId, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<Cita> buscarPorPacienteId(Long pacienteId);
    List<Cita> buscarPorMedicoId(Long medicoId);
    java.util.Optional<Cita> obtenerPorId(Long id);
    void eliminar(Long id);
}
