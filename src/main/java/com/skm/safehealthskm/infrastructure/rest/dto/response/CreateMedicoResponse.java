package com.skm.safehealthskm.infrastructure.rest.dto.response;

import com.skm.safehealthskm.domain.model.Medico;

public record CreateMedicoResponse(
        Long id,
        String nombre,
        String email,
        String especialidad
) {
    public static CreateMedicoResponse fromDomain(Medico medico){
        return new CreateMedicoResponse(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail().value(),
                medico.getEspecialidad().getNombreMostrable()
        );
    }
}
