package com.ejemploprueba.prue.repository;

import com.ejemploprueba.prue.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}