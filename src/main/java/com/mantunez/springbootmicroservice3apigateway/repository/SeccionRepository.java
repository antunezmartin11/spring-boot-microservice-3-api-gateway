package com.mantunez.springbootmicroservice3apigateway.repository;


import com.mantunez.springbootmicroservice3apigateway.model.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeccionRepository extends JpaRepository<Seccion, Long> {

    public Seccion findByNombre(String nombre);

    @Query(value = "select * from seccion where estado=true", nativeQuery = true)
    public List<Seccion> getAllSeccionActivo();
}
