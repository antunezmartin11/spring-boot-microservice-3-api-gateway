package com.mantunez.springbootmicroservice3apigateway.controller;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Grado;
import com.mantunez.springbootmicroservice3apigateway.model.Seccion;
import com.mantunez.springbootmicroservice3apigateway.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/seccion")
public class SeccionController {

    @Autowired
    private SeccionService seccionService;

    @PostMapping("/register")
    public RespuestaDTO createSeccion(@RequestBody Seccion request){
        return seccionService.saveSeccion(request);
    }

    @GetMapping("/getAllSeccion")
    public RespuestaDTO getSeccionActivo(){
        return seccionService.getAllSeccion();
    }

    @PostMapping("/updateSeccion")
    public RespuestaDTO updateSeccion(@RequestBody Seccion request){
        return seccionService.updateSeccion(request);
    }
    @PostMapping("/deleteSeccion/{id}")
    public RespuestaDTO deleteSeccion(@PathVariable Long id){
        return seccionService.deleteSeccion(id);
    }
}
