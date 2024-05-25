package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.Tipo_Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoDocumentoRepository extends JpaRepository<Tipo_Documento, Long> {

    public Tipo_Documento findByNombre(String nombre);

    @Query(value = "SELECT * FROM tipo_documento WHERE eliminado = 0 " +
            "AND (:nombre IS NULL OR nombre LIKE CONCAT('%', :nombre, '%'))",
            nativeQuery = true)
    public List<Tipo_Documento> getTipoDocumentoActivo(@Param("nombre") String nombre);
}
