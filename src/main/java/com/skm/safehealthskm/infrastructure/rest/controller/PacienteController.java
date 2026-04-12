package com.skm.safehealthskm.infrastructure.rest.controller;

import com.skm.safehealthskm.application.service.PacienteService;
import com.skm.safehealthskm.domain.model.Paciente;
import com.skm.safehealthskm.infrastructure.rest.dto.request.CreatePacienteRequest;
import com.skm.safehealthskm.infrastructure.rest.dto.response.CreatePacienteResponse;
import com.skm.safehealthskm.infrastructure.rest.dto.response.GetPacienteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @PostMapping
    public ResponseEntity<CreatePacienteResponse> crear(@Valid @RequestBody CreatePacienteRequest requestDto) {
        // El service hace toda la magia y nos devuelve el objeto con ID
        Paciente guardado = service.registrarPaciente(requestDto);

        // antes de implementar la factoría en el record haciamos:  Construimos la respuesta usando los datos del objeto que volvió de la BD
//        CreatePacienteResponse res = new CreatePacienteResponse(
//                guardado.getId(),
//                guardado.getNombre(),
//                guardado.getEmail().value()
//        );
        // ahora solo:
        return ResponseEntity.status(HttpStatus.CREATED).body(CreatePacienteResponse.fromDomain(guardado));
    }


    @GetMapping("/{id}")
    public ResponseEntity<GetPacienteResponse> obtenerPorId(@PathVariable Long id){
        Paciente buscado = service.obtenerPorId(id);
        return ResponseEntity.ok(GetPacienteResponse.fromDomain(buscado));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<GetPacienteResponse> obtenerPorEmail(@PathVariable String email){
        Paciente paciente = service.obtenerPacientePorEmail(email);
        // Usamos el metodo estatico del DTO. ¡Limpio y elegante! ;)
        return ResponseEntity.ok(GetPacienteResponse.fromDomain(paciente));
    }






    @GetMapping
    public ResponseEntity<java.util.List<GetPacienteResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos().stream().map(GetPacienteResponse::fromDomain).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetPacienteResponse> actualizar(@PathVariable Long id, @Valid @RequestBody com.skm.safehealthskm.infrastructure.rest.dto.request.UpdatePacienteRequest request) {
        Paciente actualizado = service.actualizarPaciente(id, request);
        return ResponseEntity.ok(GetPacienteResponse.fromDomain(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }

}
