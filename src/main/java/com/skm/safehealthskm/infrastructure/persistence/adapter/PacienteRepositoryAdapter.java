package com.skm.safehealthskm.infrastructure.persistence.adapter;

import com.skm.safehealthskm.domain.criterio.PacienteSearchCriteria;
import com.skm.safehealthskm.domain.model.Paciente;
import com.skm.safehealthskm.domain.model.valueobjects.Email;
import com.skm.safehealthskm.domain.repository.PacienteRepository;
import com.skm.safehealthskm.infrastructure.persistence.entity.PacienteEntity;
import com.skm.safehealthskm.infrastructure.persistence.mapper.PacienteMapper;
import com.skm.safehealthskm.infrastructure.persistence.repository.JpaPacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PacienteRepositoryAdapter implements PacienteRepository {

    private final JpaPacienteRepository jpa;
    private final PacienteMapper pacienteMapper;

    @Override
    public Optional<Paciente> obtenerPacientePorId(Long id) {
        return jpa.findById(id).map(pacienteMapper::toDomain);
    }

    @Override
    public Paciente guardar(Paciente paciente) {

        PacienteEntity entity = pacienteMapper.toEntity(paciente);

        PacienteEntity guardado = jpa.save(entity);

        return pacienteMapper.toDomain(guardado);
    }

    @Override
    public Optional<Paciente> obtenerPorEmail(String email) {
        Email emailVO = new Email(email);
        return jpa.findByEmail(emailVO).map(pacienteMapper::toDomain);
    }

    @Override
    public boolean existePorEmail(Email email) {
        return jpa.existsByEmail(email);
    }

    @Override
    public List<Paciente> listarTodos() {
        return jpa.findAll().stream().map(pacienteMapper::toDomain).toList();
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public Optional<Paciente> buscarCriterio(PacienteSearchCriteria criteria) {
        return  Optional.empty();
    }

}
