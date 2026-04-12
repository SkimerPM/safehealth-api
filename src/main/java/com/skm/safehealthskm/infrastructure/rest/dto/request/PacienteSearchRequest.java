package com.skm.safehealthskm.infrastructure.rest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PacienteSearchRequest(
        @NotNull(message = "El email es obligatorio.")
        @Email String email
) {
}