package com.ejemploprueba.prue.service;

import com.ejemploprueba.prue.model.Persona;
import com.ejemploprueba.prue.repository.PersonaRepository;
import com.ejemploprueba.prue.request.PersonaRequest;
import com.ejemploprueba.prue.response.PersonaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    // Metodo para crear una nueva persona
    public PersonaResponse crearPersona(PersonaRequest personaRequest) {
        // Aquí se crea una nueva instancia de la clase Persona
        Persona persona = new Persona();
        // Se configuran los atributos de esta instancia usando los valores del objeto PersonaRequest
        persona.setNombre(personaRequest.getNombre());
        persona.setEdad(personaRequest.getEdad());
        persona.setPais(personaRequest.getPais());
        // Se guarda la nueva instancia de Persona en la base de datos a través del repositorio

        Persona nuevaPersona = personaRepository.save(persona);
        // Se convierte la entidad Persona a un objeto PersonaResponse
        return mapToResponse(nuevaPersona);
    }

    // Metodo para mapear(convertir) una entidad Persona a PersonaResponse
    private PersonaResponse mapToResponse(Persona persona) {
        PersonaResponse personaResponse = new PersonaResponse();
        personaResponse.setId(persona.getId());
        personaResponse.setNombre(persona.getNombre());
        personaResponse.setEdad(persona.getEdad());
        personaResponse.setPais(persona.getPais());
        return personaResponse;
    }
    public List<PersonaResponse> listarPersonas() {
        List<Persona> personas = personaRepository.findAll();
        return personas.stream().map(persona -> mapToResponse(persona)).collect(Collectors.toList());
    }
}
