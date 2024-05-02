package com.mantunez.springbootmicroservice3apigateway.service;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Administrativo;

public interface AdmnistrativoService {
    RespuestaDTO saveAdministrativo(Administrativo request);

    RespuestaDTO getAllAdministrativo();

    RespuestaDTO updateAdminsitrativo(Administrativo request);

    RespuestaDTO deleteAdministrativo(Long id);
}
