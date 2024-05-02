package com.mantunez.springbootmicroservice3apigateway.service;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Responsable;

public interface ResponsableService {
    RespuestaDTO saveResponsable(Responsable request);
}
