package com.skm.safehealthskm.infrastructure.rest.dto.response;

import com.skm.safehealthskm.domain.model.Medico;

public record GetMedicoResponse(
        Long id,
        String nombre,
        String email,
        String especialidad
) {
    public static GetMedicoResponse fromDomain(Medico medico){
        return new GetMedicoResponse(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail().value(),
                medico.getEspecialidad().getNombreMostrable() // Aquí brilla mi weabdaaaa :))
        );
    }
}
