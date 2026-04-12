package com.skm.safehealthskm.infrastructure.persistence.repository;

import com.skm.safehealthskm.domain.model.valueobjects.Email;
import com.skm.safehealthskm.infrastructure.persistence.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPacienteRepository extends JpaRepository<PacienteEntity, Long> {
    Optional<PacienteEntity> findByEmail(Email email);
    boolean existsByEmail(Email email);

}
