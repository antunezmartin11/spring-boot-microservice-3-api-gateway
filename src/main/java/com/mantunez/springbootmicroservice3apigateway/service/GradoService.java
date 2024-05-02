package com.mantunez.springbootmicroservice3apigateway.service;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Grado;

public interface GradoService {
    RespuestaDTO saveGrado(Grado request);

    RespuestaDTO getAllGrado();

    RespuestaDTO updateGrado(Grado request);

    RespuestaDTO deleteGrado(Long id);
}
