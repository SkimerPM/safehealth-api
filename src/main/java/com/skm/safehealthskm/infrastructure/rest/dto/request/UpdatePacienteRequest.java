package com.skm.safehealthskm.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdatePacienteRequest(
        @NotBlank(message = "El nombre debe estar presente y no debe ser solo espacios vacíos.")
        String nombre,

        @NotBlank(message = "El email debe estar presente y no debe ser solo espacios vacíos.")
        String email
) {
}
