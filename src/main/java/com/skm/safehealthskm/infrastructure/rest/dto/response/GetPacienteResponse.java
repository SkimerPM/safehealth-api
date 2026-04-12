package com.skm.safehealthskm.infrastructure.rest.dto.response;

import com.skm.safehealthskm.domain.model.Paciente;

public record GetPacienteResponse(Long id, String nombre, String email) {
    // El "traductor" de salida que limpia el Controller
    public static GetPacienteResponse fromDomain(Paciente paciente) {
        return new GetPacienteResponse(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail().value() // Extraemos el String del Value Object
        );
    }
}