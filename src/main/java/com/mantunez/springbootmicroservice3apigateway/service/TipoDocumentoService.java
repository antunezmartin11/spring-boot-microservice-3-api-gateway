package com.mantunez.springbootmicroservice3apigateway.service;

import com.mantunez.springbootmicroservice3apigateway.model.Tipo_Documento;
import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;

import java.util.List;

public interface TipoDocumentoService {
    RespuestaDTO register(Tipo_Documento tipoDoc) throws Exception;

    List<Tipo_Documento> getTipoDocumento() throws Exception;

    RespuestaDTO updateTipoDocumento(Tipo_Documento request) throws Exception;

    RespuestaDTO deleteTipoDocumento(Long id) throws Exception;
}
