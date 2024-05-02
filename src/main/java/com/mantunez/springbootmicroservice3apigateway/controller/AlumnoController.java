package com.mantunez.springbootmicroservice3apigateway.controller;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Alumno;
import com.mantunez.springbootmicroservice3apigateway.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;


    @PostMapping("/register")
    public RespuestaDTO saveAlumno(@RequestBody Alumno request){
        return alumnoService.saveAlumno(request);
    }
}
