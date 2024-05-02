package com.mantunez.springbootmicroservice3apigateway.service;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.TipoContrato;

public interface TipoContratoService {
    RespuestaDTO saveTipoContrato(TipoContrato request);

    RespuestaDTO updateTipoContrato(TipoContrato request);

    RespuestaDTO listarTipoContrato();

    Boolean delete(Long id);
}
