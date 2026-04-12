package com.skm.safehealthskm.domain.model.enums;

import com.skm.safehealthskm.domain.exception.BusinessException;

public enum Especialidad {
    CARDIOLOGIA("Cardiología"),
    PEDIATRIA("Pediatría"),
    MEDICINA_GENERAL("Medicina general");

    private final String nombreMostrable;

    Especialidad(String nombreMostrable) {
        this.nombreMostrable = nombreMostrable;
    }
    public static Especialidad fromString(String valor){
        try{
            return Especialidad.valueOf(valor.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("La especialidad '" + valor + "' no es válida.");
        }
    }
    public String getNombreMostrable() {
        return nombreMostrable;
    }
}
