package com.skm.safehealthskm.infrastructure.rest.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CitaRequest(
        @NotNull(message = "El paciente es obligatorio.")
        Long pacienteId,
        @NotNull(message = "El Medico es obligatorio.")
        Long medicoId,
        @NotNull(message = "La fecha es obligatoria")
        @Future(message = "La fecha debe ser futura")
        LocalDateTime fechaInicio,
        @NotBlank(message = "El tipo de la cita es obligatorio.")
        String tipo
) {
}
