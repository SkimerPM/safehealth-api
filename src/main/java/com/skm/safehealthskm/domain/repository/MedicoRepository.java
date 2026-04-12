package com.skm.safehealthskm.domain.repository;

import com.skm.safehealthskm.domain.model.Medico;
import com.skm.safehealthskm.domain.model.enums.Especialidad;
import com.skm.safehealthskm.domain.model.valueobjects.Email;
import java.util.List;

import java.util.Optional;

public interface MedicoRepository {
    Medico guardar(Medico medico);
    Optional<Medico> obtenerPorId(Long id);

    boolean obtenerPorEmail(Email email);
    List<Medico> listarTodos();
    List<Medico> obtenerPorEspecialidad(Especialidad especialidad);
    void eliminar(Long id);
}
