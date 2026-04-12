package com.skm.safehealthskm.infrastructure.persistence.repository;

import com.skm.safehealthskm.domain.model.valueobjects.Email;
import com.skm.safehealthskm.infrastructure.persistence.entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMedicoRepository extends JpaRepository<MedicoEntity, Long> {

    boolean existsByEmail(Email email);
    java.util.List<MedicoEntity> findByEspecialidad(com.skm.safehealthskm.domain.model.enums.Especialidad especialidad);
}
