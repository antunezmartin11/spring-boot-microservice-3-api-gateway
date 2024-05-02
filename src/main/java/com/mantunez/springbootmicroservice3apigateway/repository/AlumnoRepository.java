package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {


    @Query(value = "SELECT * FROM alumno WHERE id_persona= ?1", nativeQuery = true)
    public Optional<Alumno> findByIdPersona(Long id_persona);
}
