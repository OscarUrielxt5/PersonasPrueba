package com.ejemploprueba.prue.service;

import com.ejemploprueba.prue.model.Persona;
import com.ejemploprueba.prue.repository.PersonaRepository;
import com.ejemploprueba.prue.request.PersonaRequest;
import com.ejemploprueba.prue.response.PersonaResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaService personaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void crearPersona() {
      PersonaRequest personaRequest = new PersonaRequest();
      personaRequest.setNombre("Jose");
      personaRequest.setPais("Mexico");
      personaRequest.setEdad(29);
      Persona persona = new Persona();
      persona.setNombre(personaRequest.getNombre());
      persona.setPais(personaRequest.getPais());
      persona.setEdad(personaRequest.getEdad());
      persona.setId(1L);
      when(personaRepository.save(any(Persona.class))).thenReturn(persona);
      PersonaResponse personaResponse = personaService.crearPersona(personaRequest);
      assertNotNull(personaResponse);
      assertEquals("Jose", personaResponse.getNombre());
      assertEquals("Mexico", personaResponse.getPais());
      assertEquals(29, personaResponse.getEdad());
      assertEquals(1L, personaResponse.getId());
      verify(personaRepository, times(1)).save(any(Persona.class));
    }

    @Test
    void listarPersonas() {
        Persona persona1= new Persona();
        persona1.setId(1L);
        persona1.setNombre("Jose");
        persona1.setPais("Mexico");
        persona1.setEdad(29);

        Persona persona2= new Persona();
        persona2.setId(2L);
        persona2.setNombre("Jose");
        persona2.setPais("Mexico");
        persona2.setEdad(29);

        when(personaRepository.findAll()).thenReturn(List.of(persona1,persona2));

        //Llamamos el metodo

        List<PersonaResponse> personas = personaService.listarPersonas();
        assertNotNull(personas);
        assertEquals(2, personas.size());
        assertEquals("Jose", personas.get(0).getNombre()); // El nombre de la primera persona es Juan
        assertEquals("Jose", personas.get(1).getNombre());
        verify(personaRepository, times(1)).findAll();

    }
}