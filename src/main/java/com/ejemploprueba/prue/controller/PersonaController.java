package com.ejemploprueba.prue.controller;

import com.ejemploprueba.prue.request.PersonaRequest;
import com.ejemploprueba.prue.response.PersonaResponse;
import com.ejemploprueba.prue.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public PersonaResponse createPersona(@RequestBody PersonaRequest personaRequest) {
       return personaService.crearPersona(personaRequest);
    }

    @GetMapping
    public List<PersonaResponse> listPersonas(@RequestBody PersonaRequest personaRequest) {
        return personaService.listarPersonas();
    }
}
