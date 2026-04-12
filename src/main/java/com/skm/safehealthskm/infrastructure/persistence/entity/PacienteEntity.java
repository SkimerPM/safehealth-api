package com.skm.safehealthskm.infrastructure.persistence.entity;

import com.skm.safehealthskm.domain.model.valueobjects.Email;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "pacientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private Email email;
}
