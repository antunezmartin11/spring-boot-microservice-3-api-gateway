package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.Grado;
import com.mantunez.springbootmicroservice3apigateway.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GradoRepository extends JpaRepository<Grado, Long> {

    //@Query(value = "SELECT * FROM persona WHERE numero_documento= ?1", nativeQuery = true)
    public Grado findByNombre(String nombre);

    @Query(value = "select * from grado where estado=true", nativeQuery = true)
    public List<Grado> getAllGradoActivo();
}
