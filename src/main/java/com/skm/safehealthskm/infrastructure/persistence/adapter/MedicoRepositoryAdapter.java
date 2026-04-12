package com.skm.safehealthskm.infrastructure.persistence.adapter;

import com.skm.safehealthskm.domain.model.Medico;
import com.skm.safehealthskm.domain.model.valueobjects.Email;
import com.skm.safehealthskm.domain.repository.MedicoRepository;
import com.skm.safehealthskm.infrastructure.persistence.entity.MedicoEntity;
import com.skm.safehealthskm.infrastructure.persistence.mapper.MedicoMapper;
import com.skm.safehealthskm.infrastructure.persistence.repository.JpaMedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.skm.safehealthskm.domain.model.enums.Especialidad;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Repository
public class MedicoRepositoryAdapter implements MedicoRepository {

    private final JpaMedicoRepository jpa;
    private final MedicoMapper mapper;

    @Override
    public Medico guardar(Medico medico) {
        if(medico == null) return null;
        MedicoEntity entity = mapper.toEntity(medico);
        MedicoEntity saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Medico> obtenerPorId(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean obtenerPorEmail(Email email) {
        return jpa.existsByEmail(email);
    }

    @Override
    public List<Medico> listarTodos() {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Medico> obtenerPorEspecialidad(Especialidad especialidad) {
        return jpa.findByEspecialidad(especialidad).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }
}
