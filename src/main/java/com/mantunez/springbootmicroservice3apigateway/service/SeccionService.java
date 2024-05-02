package com.mantunez.springbootmicroservice3apigateway.service;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Seccion;

public interface SeccionService {
    RespuestaDTO saveSeccion(Seccion request);

    RespuestaDTO getAllSeccion();

    RespuestaDTO updateSeccion(Seccion request);

    RespuestaDTO deleteSeccion(Long id);
}
