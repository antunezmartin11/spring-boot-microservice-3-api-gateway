package com.mantunez.springbootmicroservice3apigateway.service;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Docente;

public interface DocenteService {
    RespuestaDTO saveDocente(Docente request);

    RespuestaDTO listarDocente();

    RespuestaDTO updateDocente(Docente request);

    RespuestaDTO deleteDocente(Long id);
}
