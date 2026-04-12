package com.skm.safehealthskm.domain.model.valueobjects;

import com.skm.safehealthskm.domain.exception.BusinessException;

public record Email(String value) {
    public Email{
        // 1. Limpiamos ANTES de validar
        if (value != null) {
            value = value.trim().toLowerCase();
        }

        if (value == null || value.isBlank()){
            throw new BusinessException("El email no puede estar vacío.");
        }
        if (!value.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new BusinessException("El formato del email '" + value + "' es inválido.");
        }
    }
    @Override
    public String toString() {
        return value;
    }
}
