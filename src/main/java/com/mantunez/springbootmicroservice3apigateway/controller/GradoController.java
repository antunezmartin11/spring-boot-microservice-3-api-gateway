package com.mantunez.springbootmicroservice3apigateway.controller;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Grado;
import com.mantunez.springbootmicroservice3apigateway.service.GradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/grado")
public class GradoController {

    @Autowired
    private GradoService gradoService;

    @PostMapping("/register")
    public RespuestaDTO createGrade(@RequestBody Grado grado){
        return gradoService.saveGrado(grado);
    }

    @GetMapping("/getAllGrado")
    public RespuestaDTO getGradoActivo(){
        return gradoService.getAllGrado();
    }

    @PostMapping("/updateGrado")
    public RespuestaDTO updateGrado(@RequestBody Grado request){
        return gradoService.updateGrado(request);
    }
    @PostMapping("/deletGrado/{id}")
    public RespuestaDTO deleteGrado(@PathVariable Long id){
        return gradoService.deleteGrado(id);
    }
}
