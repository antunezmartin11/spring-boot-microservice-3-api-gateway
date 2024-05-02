package com.mantunez.springbootmicroservice3apigateway.controller;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Responsable;
import com.mantunez.springbootmicroservice3apigateway.service.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/responsable")
public class ResponsableController {

    @Autowired
    private ResponsableService responsableService;


    @PostMapping("/register")
    public RespuestaDTO saveResponsable(@RequestBody Responsable request){
        return responsableService.saveResponsable(request);
    }
}
