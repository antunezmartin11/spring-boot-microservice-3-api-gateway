package com.mantunez.springbootmicroservice3apigateway.service.impl;

import com.mantunez.springbootmicroservice3apigateway.model.Persona;
import com.mantunez.springbootmicroservice3apigateway.service.PersonaService;
import com.mantunez.springbootmicroservice3apigateway.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Optional<Persona> getPersonaDocumento(String dni){
        return personaRepository.findByNumeroDocumento(dni);
    }

    @Override
    public Persona savePersona(Persona request){
        return personaRepository.save(request);
    }
}
