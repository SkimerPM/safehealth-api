package com.skm.safehealthskm.domain.model;
import com.skm.safehealthskm.domain.model.enums.Especialidad;

import com.skm.safehealthskm.domain.model.valueobjects.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

@Getter
@ToString
public class Medico {
    private final Long id;
    private final Email email;
    private final String nombre;
    private final Especialidad especialidad;

    @Builder
    public Medico(Long id, Email email, String nombre, Especialidad especialidad) {
        if (email == null) throw new IllegalArgumentException("El objeto Email es obligatorio.");
        if (!StringUtils.hasText(nombre)) throw new IllegalArgumentException("El nombre del médico es obligatorio");
        if (especialidad == null) throw new IllegalArgumentException("La especialidad es obligatoria");
        this.email = email;
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
}
