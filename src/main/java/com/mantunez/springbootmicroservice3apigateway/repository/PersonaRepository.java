package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Query(value = "SELECT * FROM persona WHERE numero_documento= ?1", nativeQuery = true)
    public Optional<Persona> findByNumeroDocumento(String numDoc);
}
