package com.skm.safehealthskm.domain.model.valueobjects;

import com.skm.safehealthskm.domain.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EmailTest {

    @Test
    @DisplayName("Debe crear una instancia válida cuando el formato es correcto")
    void crearEmailValido(){
        // Arrage
        String emailString =  "jose@banco.com";

        // Act
        Email email = new Email(emailString);

        // Assert
        assertNotNull(email);
        assertEquals(emailString, email.value());
    }


    @Test
    @DisplayName("Debe lanzar BussinesException cuando el formato  no tiene @")
    void lanzarExceptionEmailSinArroba(){
        // A1
        String emailinvalido = "estonoesunemail";
        // A2 y A3
        assertThrows(BusinessException.class, ()-> new Email(emailinvalido));
    }

    @Test
    @DisplayName("Debe lanzar BusinessException cuando el email es nulo o vacío")
    void lanzarExcepcionEmailVacio() {
        assertThrows(BusinessException.class, () -> new Email(""));
        assertThrows(BusinessException.class, () -> new Email(null));
    }

}
