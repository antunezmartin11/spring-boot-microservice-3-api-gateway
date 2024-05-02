package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.Persona;
import com.mantunez.springbootmicroservice3apigateway.model.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
    @Query(value = "SELECT * FROM responsable WHERE id_persona= ?1", nativeQuery = true)
    public Optional<Responsable> findByIdPersona(Long id_persona);
}
