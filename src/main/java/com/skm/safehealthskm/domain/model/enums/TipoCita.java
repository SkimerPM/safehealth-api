package com.skm.safehealthskm.domain.model.enums;

import com.skm.safehealthskm.domain.exception.BusinessException;

public enum TipoCita {
    CONSULTA_GENERAL(30), //minutos
    ESPECIALISTA(60),
    REVISION_RESULTADOS(15);

    private final int duracionMinutos;

    TipoCita(int duracionMinutos){
        this.duracionMinutos = duracionMinutos;
    }
    public int getDuracionMinutos(){
        return duracionMinutos;
    }

    public static TipoCita fromString(String tipoCita){
        try{
            return TipoCita.valueOf(tipoCita.trim().toUpperCase());
        }catch (IllegalArgumentException e){
            throw new BusinessException("El string de estado '" + tipoCita + "' no es válido.");
        }
    }
}
