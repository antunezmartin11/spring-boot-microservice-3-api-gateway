package com.mantunez.springbootmicroservice3apigateway.controller;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Area;
import com.mantunez.springbootmicroservice3apigateway.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping("/register")
    public RespuestaDTO registerArea(@RequestBody Area request){
        return areaService.saveArea(request);
    }

    @PostMapping("/getAll")
    public RespuestaDTO getAllArea(@RequestBody Area request){
        return areaService.listaArea(request);
    }

    @PostMapping("/updateArea")
    public RespuestaDTO updateArea(@RequestBody Area request){
        return areaService.updateArea(request);
    }

    @PostMapping("/deleteArea/{id}")
    public RespuestaDTO deleteArea(@PathVariable Long id){
       return areaService.deleteArea(id);
    }
}

