package com.skm.safehealthskm.domain.model;
import java.time.LocalDateTime;
import java.util.Objects;

import com.skm.safehealthskm.domain.model.enums.EstadoCita;
import com.skm.safehealthskm.domain.model.enums.TipoCita;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Cita {
    private final Long id;
    private final Paciente paciente;
    private final Medico medico;
    private final LocalDateTime fechaInicio;
    private final LocalDateTime fechaFin; //calculado
    private final TipoCita tipo;
    private final EstadoCita estado;

    @Builder
    public Cita(Long id, Paciente paciente, Medico medico, LocalDateTime fechaInicio, TipoCita tipo, EstadoCita estado) {
        // Validaciones de integridad inmediata
        if (fechaInicio == null) throw new IllegalArgumentException("La fecha de inicio no puede ser nula");
        if (tipo == null) throw new IllegalArgumentException("El tipo de cita es obligatorio");
        this.id = id;
        this.paciente = Objects.requireNonNull(paciente, "El paciente es obligatorio");
        this.medico = Objects.requireNonNull(medico, "El médico es obligatorio");
        this.fechaInicio = fechaInicio;
        this.tipo = tipo;
        // SI EL ESTADO VIENE NULO, LE ASIGNAMOS 'PENDIENTE' POR DEFECTO
        this.estado = (estado != null) ? estado : EstadoCita.PENDIENTE;
        this.fechaFin = fechaInicio.plusMinutes(tipo.getDuracionMinutos());
    }

    // REGLA DE DOMINIO: ¿Es una fecha coherente?
    public boolean tieneFechaValida() {
        if (fechaInicio == null) return false;
        return fechaInicio.isAfter(LocalDateTime.now()) &&
                fechaInicio.isBefore(LocalDateTime.now().plusWeeks(2));
    }

}
