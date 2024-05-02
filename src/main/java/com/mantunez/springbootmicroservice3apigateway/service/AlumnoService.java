package com.mantunez.springbootmicroservice3apigateway.service;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Alumno;

public interface AlumnoService {
    RespuestaDTO saveAlumno(Alumno request);
}
