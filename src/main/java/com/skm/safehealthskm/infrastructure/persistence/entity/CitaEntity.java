package com.skm.safehealthskm.infrastructure.persistence.entity;
import com.skm.safehealthskm.domain.model.enums.EstadoCita;
import com.skm.safehealthskm.domain.model.enums.TipoCita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private MedicoEntity medico;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin; //calculado
    private TipoCita tipo;
    private EstadoCita estado;
}
