package com.mantunez.springbootmicroservice3apigateway.dto;

import lombok.Data;
import reactor.util.annotation.Nullable;

@Data
public class tipoDocumentoDTO {


    private Long id;

    private String nombre;

    private String abreviatura;

    private String estado;
}
