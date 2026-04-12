package com.skm.safehealthskm.domain.repository;

import com.skm.safehealthskm.domain.criterio.PacienteSearchCriteria;
import com.skm.safehealthskm.domain.model.Paciente;
import com.skm.safehealthskm.domain.model.valueobjects.Email;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository {
    Optional<Paciente> obtenerPacientePorId(Long id);

    Paciente guardar(Paciente paciente);

    Optional<Paciente> obtenerPorEmail(String email);

    boolean existePorEmail(Email email);

    List<Paciente> listarTodos();

    void eliminar(Long id);

    //prueba
    Optional<Paciente> buscarCriterio(PacienteSearchCriteria criteria);
}
