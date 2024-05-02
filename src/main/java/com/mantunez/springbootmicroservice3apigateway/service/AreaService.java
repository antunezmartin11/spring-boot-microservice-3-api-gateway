package com.mantunez.springbootmicroservice3apigateway.service;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Area;

public interface AreaService {
    RespuestaDTO saveArea(Area request);

    RespuestaDTO listaArea();

    RespuestaDTO updateArea(Area request);

    RespuestaDTO deleteArea(Long id);
}
