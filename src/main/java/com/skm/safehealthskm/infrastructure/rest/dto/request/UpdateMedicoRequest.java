package com.skm.safehealthskm.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateMedicoRequest(
        @NotBlank(message = "El nombre es obligatorio.")
        String nombre,

        @NotBlank(message = "El email es obligatorio.")
        String email,

        @NotBlank(message = "La especialidad es obligatoria.")
        String especialidad
) {
}
