package com.skm.safehealthskm.infrastructure.rest.dto.response;
import com.skm.safehealthskm.domain.model.Cita;
import java.time.LocalDateTime;

public record CitaResponse(
        Long id,
        String pacienteNombre,
        String medicoNombre,
        String medicoEspecialidad,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        String tipo,
        String estado
) {
    public static CitaResponse fromDomain(Cita cita) {
        return new CitaResponse(
                cita.getId(),
                cita.getPaciente().getNombre(),
                cita.getMedico().getNombre(),
                cita.getMedico().getEspecialidad().getNombreMostrable(),
                cita.getFechaInicio(),
                cita.getFechaFin(),
                cita.getTipo().name(),
                cita.getEstado().name()
        );
    }
}