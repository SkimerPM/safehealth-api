package com.skm.safehealthskm.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CambiarEstadoCitaRequest(
        @NotBlank(message = "El estado es obligatorio.")
        String estado
) {
}
