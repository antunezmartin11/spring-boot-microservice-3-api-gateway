package com.mantunez.springbootmicroservice3apigateway.controller;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.TipoContrato;
import com.mantunez.springbootmicroservice3apigateway.service.TipoContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tipoContrato")
public class TipoContratoController {

    @Autowired
    private TipoContratoService tipoContratoService;

    @PostMapping("/register")
    public RespuestaDTO saveTipoContrato(@RequestBody TipoContrato request){
        return tipoContratoService.saveTipoContrato(request);
    }

    @PostMapping("/updateTipoContrato")
    public RespuestaDTO update(@RequestBody TipoContrato request){
        return tipoContratoService.updateTipoContrato(request);
    }

    @GetMapping("/listar")
    public RespuestaDTO listar(){
        return tipoContratoService.listarTipoContrato();
    }

    @PostMapping("/delete/{id}")
    public RespuestaDTO delete(@PathVariable Long id){
        Boolean respuesta = tipoContratoService.delete(id);
        RespuestaDTO resp = new RespuestaDTO();
        if(respuesta){
            resp.setMensaje("Registro eliminado");
            resp.setCodigo(String.valueOf(HttpStatus.OK.value()));
            resp.setData(true);
        }else {
            resp.setMensaje("No se encontro el registro");
            resp.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
            resp.setData(false);
        }
        return resp;
    }
}
