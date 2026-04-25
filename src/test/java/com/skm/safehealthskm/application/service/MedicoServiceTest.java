package com.skm.safehealthskm.application.service;

import com.skm.safehealthskm.domain.exception.BusinessException;
import com.skm.safehealthskm.domain.model.Medico;
import com.skm.safehealthskm.domain.model.valueobjects.Email;
import com.skm.safehealthskm.domain.repository.MedicoRepository;
import com.skm.safehealthskm.infrastructure.rest.dto.request.CreateMedicoRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Activa mockitoooo  para Junit
public class MedicoServiceTest {

    //Necesitamos un médico repository, por lo tanto, simularemos uno.
    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks // Inyecta todos los @Mock
    private MedicoService medicoService; // Crea el MedicoService

    @Test
    @DisplayName("Debe lanzar BussinesException cuando el email ya está registrado")
    void errorEmailDuplicado(){
        // A1
        String emailRepetido = "doctor.chapatin@jsjsj.com";
        CreateMedicoRequest req = new CreateMedicoRequest(emailRepetido, "doctor chapatin", "CARDIOLOGIA");
            // Entrenamos al Mock: Cuando alguien pregunte por este email, mandar la exception
            // es decir, intentamos hacer que falle a proposito "cuando te pregunten si hay un email
            // igual al de este, responde true.
            // para simplifcar podemos simplemente decirle any() en vez de new Email.....
            // porque solo intentamos hacer que falle para verificar que responda con el BussinesException.
            when(medicoRepository.obtenerPorEmail(new Email(emailRepetido))).thenReturn(true);
        // A2 y A3
        BusinessException ex = assertThrows(BusinessException.class, ()->{
            medicoService.registrarMedico(req);
        });

        assertEquals("Ya existe un médico registrado con ese email", ex.getMessage());

        //Desglosando verify:
        // verifica algo para estar seguros de que el codigo jamas intentó seguir despues de esta
        // Exception.
        // never() le indica que el test solo será existoso si el metodo siguiente tuvo cero llamadas
        // .guardar() es el metodo siguiente

        verify(medicoRepository, never()).guardar(any());
    }

    @Test
    @DisplayName("Debe registrar un medico exitosamente.")
    void registrarMedicoExito(){
        // A1
        CreateMedicoRequest request = new CreateMedicoRequest("fabi@ola.com", "fabi", "CARDIOLOGIA");

        when(medicoRepository.obtenerPorEmail(any())).thenReturn(false);

        //simulamos que al guardar nos devuelve un Medico con id 100
        when(medicoRepository.guardar(any())).thenAnswer(invocation -> {
            Medico medicoRecibido = invocation.getArgument(0);
            return Medico.builder()
                    .id(100L)
                    .email(medicoRecibido.getEmail())
                    .nombre(medicoRecibido.getNombre())
                    .especialidad(medicoRecibido.getEspecialidad())
                    .build();
        });

        // A2
        Medico resultado = medicoService.registrarMedico(request);

        // A3
        assertNotNull(resultado);
        assertEquals(100L, resultado.getId());
        assertEquals("fabi", resultado.getNombre());

        verify(medicoRepository,  times(1)).guardar(any(Medico.class));





    }


}
