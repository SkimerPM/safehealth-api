package com.skm.safehealthskm.application.service;

import com.skm.safehealthskm.domain.exception.BusinessException;
import com.skm.safehealthskm.domain.model.Medico;
import com.skm.safehealthskm.domain.model.enums.Especialidad;
import com.skm.safehealthskm.domain.model.valueobjects.Email;
import com.skm.safehealthskm.domain.repository.MedicoRepository;
import com.skm.safehealthskm.infrastructure.rest.dto.request.CreateMedicoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository repo;

    public Medico registrarMedico(CreateMedicoRequest medicoDto){

        Email emailValido = new Email(medicoDto.email());
        Especialidad especialidad = Especialidad.fromString(medicoDto.especialidad());

        if (obtenerPorEmail(emailValido)){
            throw new BusinessException("Ya existe un médico registrado con ese email");
        }

        Medico medico = Medico.builder().email(emailValido).nombre(medicoDto.nombre()).especialidad(especialidad).build();

        return repo.guardar(medico);
    }

    public Medico obtenerPorId(Long id){
        return repo.obtenerPorId(id).orElseThrow(()->new BusinessException("Médico con el id: " + id + " no encontrado"));
    }

    public boolean obtenerPorEmail(Email email){

        return repo.obtenerPorEmail(email);
    }

    public java.util.List<Medico> listarTodos() {
        return repo.listarTodos();
    }

    public java.util.List<Medico> obtenerPorEspecialidad(Especialidad especialidad) {
        return repo.obtenerPorEspecialidad(especialidad);
    }

    public Medico actualizarMedico(Long id, com.skm.safehealthskm.infrastructure.rest.dto.request.UpdateMedicoRequest request) {
        Medico existente = obtenerPorId(id);
        Email nuevoEmail = new Email(request.email());
        Especialidad nuevaEspecialidad = Especialidad.fromString(request.especialidad());

        // Validar email repetido
        if (!existente.getEmail().equals(nuevoEmail) && obtenerPorEmail(nuevoEmail)) {
            throw new BusinessException("Ya existe un médico registrado con ese email");
        }

        Medico actualizado = Medico.builder().id(id).email(nuevoEmail).nombre(request.nombre()).especialidad(nuevaEspecialidad).build();
        return repo.guardar(actualizado);
    }

    public void eliminarMedico(Long id) {
        Medico existente = obtenerPorId(id); // verifica existencia
        repo.eliminar(existente.getId());
    }
}
