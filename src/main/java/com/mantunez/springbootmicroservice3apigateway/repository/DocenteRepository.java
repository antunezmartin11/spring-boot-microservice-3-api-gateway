package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    @Query(value = "SELECT * FROM docente WHERE id_persona= ?1", nativeQuery = true)
    public Optional<Docente> findByIdPersona(Long id_persona);

}
