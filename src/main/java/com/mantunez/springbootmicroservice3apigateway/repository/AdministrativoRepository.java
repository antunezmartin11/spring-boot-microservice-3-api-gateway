package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {


    @Query(value = "SELECT * FROM administrativo WHERE estado=true", nativeQuery = true)
    public List<Administrativo> getAllAdministrativoActivo();
}
