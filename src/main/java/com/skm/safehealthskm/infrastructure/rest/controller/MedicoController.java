package com.skm.safehealthskm.infrastructure.rest.controller;

import com.skm.safehealthskm.application.service.MedicoService;
import com.skm.safehealthskm.domain.model.Medico;
import com.skm.safehealthskm.domain.model.Paciente;
import com.skm.safehealthskm.infrastructure.rest.dto.request.CreateMedicoRequest;
import com.skm.safehealthskm.infrastructure.rest.dto.response.CreateMedicoResponse;
import com.skm.safehealthskm.infrastructure.rest.dto.response.GetMedicoResponse;
import com.skm.safehealthskm.infrastructure.rest.dto.response.GetPacienteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/medico")
public class MedicoController {
    private final MedicoService service;


    @GetMapping("/{id}")
    public ResponseEntity<GetMedicoResponse> obtenerPorId(@PathVariable Long id){
        Medico buscado = service.obtenerPorId(id);
        return ResponseEntity.ok(GetMedicoResponse.fromDomain(buscado));
    }

    @PostMapping
    public ResponseEntity<CreateMedicoResponse> crear(@Valid @RequestBody CreateMedicoRequest medicoRequest){
        Medico guardado = service.registrarMedico(medicoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateMedicoResponse.fromDomain(guardado));
    }
    @GetMapping
    public ResponseEntity<java.util.List<GetMedicoResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos().stream().map(GetMedicoResponse::fromDomain).toList());
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<java.util.List<GetMedicoResponse>> listarPorEspecialidad(@PathVariable String especialidad) {
        return ResponseEntity.ok(service.obtenerPorEspecialidad(com.skm.safehealthskm.domain.model.enums.Especialidad.fromString(especialidad)).stream().map(GetMedicoResponse::fromDomain).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetMedicoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody  com.skm.safehealthskm.infrastructure.rest.dto.request.UpdateMedicoRequest request) {
        Medico actualizado = service.actualizarMedico(id, request);
        return ResponseEntity.ok(GetMedicoResponse.fromDomain(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarMedico(id);
        return ResponseEntity.noContent().build();
    }
}
