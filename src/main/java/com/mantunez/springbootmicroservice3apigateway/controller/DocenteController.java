package com.mantunez.springbootmicroservice3apigateway.controller;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Docente;
import com.mantunez.springbootmicroservice3apigateway.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/docente")
public class DocenteController {


    @Autowired
    private DocenteService docenteService;

    @PostMapping("/register")
    public RespuestaDTO saveDocente(@RequestBody Docente request){
        return docenteService.saveDocente(request);
    }

    @GetMapping("/getAllDocente")
    public RespuestaDTO getAllDocente(){
        return docenteService.listarDocente();
    }

    @PostMapping("/updateDocente")
    public RespuestaDTO updateDocente(@RequestBody Docente request){
        return docenteService.updateDocente(request);
    }

    @PostMapping("deleteDocente/{id}")
    public RespuestaDTO deleteDocente(@PathVariable Long id){
        return docenteService.deleteDocente(id);
    }
}
