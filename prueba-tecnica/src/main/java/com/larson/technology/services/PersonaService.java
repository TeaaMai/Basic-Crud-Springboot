package com.larson.technology.services;

import com.larson.technology.models.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    List<Persona> findAll();

    Optional<Persona> findById(Long id);
    void createPersona(Persona persona);

    void deletePersonaById(Long id);

    void editPersona(Persona persona);

}
