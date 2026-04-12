package com.skm.safehealthskm.infrastructure.rest.controller;

import com.skm.safehealthskm.application.service.CitaService;
import com.skm.safehealthskm.domain.model.Cita;
import com.skm.safehealthskm.infrastructure.rest.dto.request.CitaRequest;
import com.skm.safehealthskm.infrastructure.rest.dto.response.CitaResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService service;
    @PostMapping
    public ResponseEntity<CitaResponse> agendar(@Valid @RequestBody CitaRequest request) {
        // El Service orquestará la búsqueda de Médico/Paciente y las validaciones
        Cita guardada = service.agendarCita(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(CitaResponse.fromDomain(guardada));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<CitaResponse>> listarPorPaciente(@PathVariable Long pacienteId) {
        List<CitaResponse> respuesta = service.buscarPorPacienteId(pacienteId)
                .stream()
                .map(CitaResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(CitaResponse.fromDomain(service.obtenerPorId(id)));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<CitaResponse>> listarPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(service.buscarPorMedicoId(medicoId).stream().map(CitaResponse::fromDomain).toList());
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<CitaResponse> cambiarEstado(@PathVariable Long id, @Valid @RequestBody com.skm.safehealthskm.infrastructure.rest.dto.request.CambiarEstadoCitaRequest request) {
        return ResponseEntity.ok(CitaResponse.fromDomain(service.cambiarEstadoCita(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarCita(@PathVariable Long id) {
        service.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }
}