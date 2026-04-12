package com.skm.safehealthskm.infrastructure.persistence.entity;

import com.skm.safehealthskm.domain.model.enums.Especialidad;
import com.skm.safehealthskm.domain.model.valueobjects.Email;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;

    private Email email;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
}
