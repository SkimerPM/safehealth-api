package com.skm.safehealthskm.infrastructure.rest.dto.request;

import com.skm.safehealthskm.domain.model.enums.Especialidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateMedicoRequest(

//        @Email(message = "Ingrese un email valido.")
        @NotBlank
        String email,
        @NotBlank
        String nombre,

        @NotBlank
        String especialidad
) { }
