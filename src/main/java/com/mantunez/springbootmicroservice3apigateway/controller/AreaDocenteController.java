package com.mantunez.springbootmicroservice3apigateway.controller;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.AreaDocente;
import com.mantunez.springbootmicroservice3apigateway.service.AreaDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/areaDocente")
public class AreaDocenteController {

    @Autowired
    private AreaDocenteService areaDocenteService;


    @PostMapping("/register")
    public RespuestaDTO guardarAreaDocente(@RequestBody AreaDocente request){
        return areaDocenteService.saveAreaDocente(request);
    }

    @GetMapping("/getAll")
    public RespuestaDTO getAll(){
        return areaDocenteService.getAreaDocente();
    }

    @PostMapping("/updateAreaDocente")
    public RespuestaDTO update(@RequestBody AreaDocente request){
        return areaDocenteService.updateAreaDocente(request);
    }

    @PostMapping("/deleteAreaDocente/{id}")
    public RespuestaDTO delete(@PathVariable Long id){
        return areaDocenteService.deleteAreaDocente(id);
    }
}
