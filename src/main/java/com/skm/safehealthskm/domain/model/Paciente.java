package com.skm.safehealthskm.domain.model;

import com.skm.safehealthskm.domain.model.valueobjects.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

@Getter
@ToString
public class Paciente {
    private final Long id;
    private final String nombre;
    private final Email email;

    @Builder
    public Paciente(Long id, String nombre, Email email) {
        if (!StringUtils.hasText(nombre))
            throw new IllegalArgumentException("El nombre del paciente es obligatorio");
        if (email == null)
            throw new IllegalArgumentException("El email es obligatorio.");

        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }
}