package com.mantunez.springbootmicroservice3apigateway.controller;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Administrativo;
import com.mantunez.springbootmicroservice3apigateway.service.AdmnistrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/administrativo")

public class AdministrativoController {


    @Autowired
    private AdmnistrativoService admnistrativoService;



    @PostMapping("/register")
    public RespuestaDTO saveAdministrativo(@RequestBody Administrativo request){
        return admnistrativoService.saveAdministrativo(request);
    }

    @GetMapping("/getAllAdministrativo")
    public RespuestaDTO getAll(){
        return admnistrativoService.getAllAdministrativo();
    }

    @PutMapping("/updateAdminsitrativo")
    public RespuestaDTO updateAdministrativo(@RequestBody Administrativo request){
        return admnistrativoService.updateAdminsitrativo(request);
    }

    @PostMapping("/deleteAdministrativo/{id}")
    public RespuestaDTO deleteAdministativo(@PathVariable Long id){
        return admnistrativoService.deleteAdministrativo(id);
    }
}
