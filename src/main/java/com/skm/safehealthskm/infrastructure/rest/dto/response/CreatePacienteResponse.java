package com.skm.safehealthskm.infrastructure.rest.dto.response;

import com.skm.safehealthskm.domain.model.Paciente;

public record CreatePacienteResponse (
        Long id,
        String nombre,
        String email
){
    public static CreatePacienteResponse fromDomain(Paciente paciente){
        return new CreatePacienteResponse(paciente.getId(),paciente.getNombre(),paciente.getEmail().value());
    }
}
