package com.mantunez.springbootmicroservice3apigateway.repository;

import com.mantunez.springbootmicroservice3apigateway.model.TipoContrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoContratoRepository extends JpaRepository<TipoContrato, Long> {

    public TipoContrato findByNombre(String nombre);
}
