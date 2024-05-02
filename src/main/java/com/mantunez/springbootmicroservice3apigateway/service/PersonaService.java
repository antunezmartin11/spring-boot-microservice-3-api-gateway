package com.mantunez.springbootmicroservice3apigateway.service;

import com.mantunez.springbootmicroservice3apigateway.model.Persona;

import java.util.Optional;

public interface PersonaService {
    Optional<Persona> getPersonaDocumento(String dni);

    Persona savePersona(Persona request);
}
