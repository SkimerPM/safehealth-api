package com.skm.safehealthskm.domain.model.enums;

import com.skm.safehealthskm.domain.exception.BusinessException;

public enum EstadoCita {
    PENDIENTE, // Creada - esperando pago
    CONFIRMADA, // Pagada
    CANCELADA, // Anulada por el usuario o el sistema
    CULMINADA; // Atención médica realizada

    public static EstadoCita fromString(String estadoString){
        try{
            return EstadoCita.valueOf(estadoString.trim().toUpperCase());
        }catch (IllegalArgumentException e){
            throw new BusinessException("El string de estado '" + estadoString + "' no es válido.");
        }
    }
}
