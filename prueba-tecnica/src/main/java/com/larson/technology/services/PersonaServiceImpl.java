package com.larson.technology.services;

import com.larson.technology.models.Persona;
import com.larson.technology.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    private PersonaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll() {
        return (List<Persona>) repository.findAll();
    }

    @Override
    public Optional<Persona> findById(Long id) {

        return repository.findById(id);
    }

    @Override
    @Transactional
    public void createPersona(Persona persona) {
        if(Objects.nonNull(persona)){

            repository.save(persona);
        }

    }

    @Override
    @Transactional
    public void deletePersonaById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void editPersona(Persona persona) {
        persona.setId(persona.getId());
        repository.save(persona);

    }


}
