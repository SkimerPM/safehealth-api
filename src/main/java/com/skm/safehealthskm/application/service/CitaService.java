package com.skm.safehealthskm.application.service;

import com.skm.safehealthskm.domain.exception.BusinessException;
import com.skm.safehealthskm.domain.model.Cita;

import com.skm.safehealthskm.domain.model.Medico;
import com.skm.safehealthskm.domain.model.Paciente;
import com.skm.safehealthskm.domain.model.enums.TipoCita;
import com.skm.safehealthskm.domain.repository.CitaRepository;
import com.skm.safehealthskm.infrastructure.rest.dto.request.CitaRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final MedicoService medicoService; // Inyectamos para buscar
    private final PacienteService pacienteService; // Inyectamos para buscar
    @Transactional
    public Cita agendarCita(CitaRequest dto) {
        // 1. Transformamos IDs a Objetos de Dominio reales
        Medico medico = medicoService.obtenerPorId(dto.medicoId());
        Paciente paciente = pacienteService.obtenerPorId(dto.pacienteId());

        // 2. Creamos el objeto de dominio usando el Builder
        // Aquí el constructor de Cita calculará fechaFin y pondrá estado PENDIENTE
        Cita cita = Cita.builder()
                .medico(medico)
                .paciente(paciente)
                .fechaInicio(dto.fechaInicio())
                .tipo(TipoCita.fromString(dto.tipo()))
                .build();

        // 3. Validación de Regla de Negocio (Fecha válida)
        if (!cita.tieneFechaValida()) {
            throw new BusinessException("La fecha de la cita debe ser futura (máx 2 semanas).");
        }

        // 4. Validación de Solapamiento (Disponibilidad del médico)
        boolean ocupado = citaRepository.existeCitaEnRango(
                medico.getId(),
                cita.getFechaInicio(),
                cita.getFechaFin()
        );

        if (ocupado) {
            throw new BusinessException("El médico ya tiene una cita programada en ese horario.");
        }

        // 5. Persistencia
        return citaRepository.guardar(cita);
    }

    public boolean existeCitaEnRango(Long medicoId, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return citaRepository.existeCitaEnRango(medicoId, fechaInicio, fechaFin);
    }

    public List<Cita> buscarPorPacienteId(Long pacienteId){
        return citaRepository.buscarPorPacienteId(pacienteId);
    }

    public Cita obtenerPorId(Long id) {
        return citaRepository.obtenerPorId(id).orElseThrow(() -> new BusinessException("Cita con id " + id + " no encontrada"));
    }

    public List<Cita> buscarPorMedicoId(Long medicoId){
        return citaRepository.buscarPorMedicoId(medicoId);
    }

    @Transactional
    public Cita cambiarEstadoCita(Long id, com.skm.safehealthskm.infrastructure.rest.dto.request.CambiarEstadoCitaRequest request) {
        Cita citaExistente = obtenerPorId(id);
        com.skm.safehealthskm.domain.model.enums.EstadoCita nuevoEstado = com.skm.safehealthskm.domain.model.enums.EstadoCita.fromString(request.estado());
        
        Cita citaActualizada = Cita.builder()
                .id(citaExistente.getId())
                .paciente(citaExistente.getPaciente())
                .medico(citaExistente.getMedico())
                .fechaInicio(citaExistente.getFechaInicio())
                .tipo(citaExistente.getTipo())
                .estado(nuevoEstado)
                .build();
                
        return citaRepository.guardar(citaActualizada);
    }

    public void eliminarCita(Long id) {
        Cita citaExistente = obtenerPorId(id);
        citaRepository.eliminar(citaExistente.getId());
    }
}

