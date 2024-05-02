package com.mantunez.springbootmicroservice3apigateway.service;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.AreaDocente;

public interface AreaDocenteService {
    RespuestaDTO saveAreaDocente(AreaDocente request);

    RespuestaDTO getAreaDocente();

    RespuestaDTO updateAreaDocente(AreaDocente request);

    RespuestaDTO deleteAreaDocente(Long id);
}
