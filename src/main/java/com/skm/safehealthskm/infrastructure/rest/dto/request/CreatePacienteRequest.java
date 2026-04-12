package com.skm.safehealthskm.infrastructure.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreatePacienteRequest(
        @NotBlank(message = "El nombre debe estar presente y no debe ser solo espacios vacíos.")
        String nombre,

        @NotBlank(message = "El email debe estar presente y no debe ser solo espacios vacíos.")
//        @Email(message = "Ingresa un email válido.")
        String email
) {
}
