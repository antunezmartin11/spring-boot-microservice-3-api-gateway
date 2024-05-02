package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.Tipo_Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoDocumentoRepository extends JpaRepository<Tipo_Documento, Long> {

    public Tipo_Documento findByNombre(String nombre);

    @Query(value = "select * from tipo_documento where estado=1", nativeQuery = true)
    public List<Tipo_Documento> getTipoDocumentoActivo();
}
