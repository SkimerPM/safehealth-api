package com.skm.safehealthskm.application.service;

import com.skm.safehealthskm.domain.exception.BusinessException;
import com.skm.safehealthskm.domain.exception.ResourceNotFoundException;
import com.skm.safehealthskm.domain.model.Paciente;
import com.skm.safehealthskm.domain.model.valueobjects.Email;
import com.skm.safehealthskm.domain.repository.PacienteRepository;
import com.skm.safehealthskm.infrastructure.rest.dto.request.CreatePacienteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository repository;


    public Paciente registrarPaciente(CreatePacienteRequest request){

        Email emailValido = new Email(request.email());

        // validar que no exista un paciente con el mismo email
        boolean existe = emailRegistrado(emailValido.value());
        if (existe){
            throw new BusinessException("Ya existe un paciente registrado con ese email");
        }

        Paciente nuevoPaciente = Paciente.builder()
                .nombre(request.nombre())
                .email(emailValido)
                .build();

        return repository.guardar(nuevoPaciente);
    }

    public Paciente obtenerPorId(Long id){
        return repository.obtenerPacientePorId(id).orElseThrow(()-> new BusinessException("Paciente con id "+id+ " no encontrado."));
    }

    public Paciente obtenerPacientePorEmail(String emailString) {

        Email emailValido = new Email(emailString);

        return repository.obtenerPorEmail(emailValido.value())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el paciente con email: " + emailValido.value()));
    }

    public boolean emailRegistrado(String emailString) {
        Email emailValido = new Email(emailString);

        return repository.existePorEmail(emailValido);
    }

    public java.util.List<Paciente> listarTodos() {
        return repository.listarTodos();
    }

    public Paciente actualizarPaciente(Long id, com.skm.safehealthskm.infrastructure.rest.dto.request.UpdatePacienteRequest request) {
        Paciente existente = obtenerPorId(id);
        Email nuevoEmail = new Email(request.email());

        // Validar si el email existe en OTRO paciente
        if (!existente.getEmail().equals(nuevoEmail) && emailRegistrado(nuevoEmail.value())) {
            throw new BusinessException("Ya existe un paciente registrado con ese email");
        }

        Paciente actualizado = Paciente.builder()
                .id(existente.getId())
                .nombre(request.nombre())
                .email(nuevoEmail)
                .build();

        return repository.guardar(actualizado);
    }

    public void eliminarPaciente(Long id) {
        Paciente existente = obtenerPorId(id);
        repository.eliminar(existente.getId());
    }
}
