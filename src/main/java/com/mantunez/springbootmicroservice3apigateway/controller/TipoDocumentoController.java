package com.mantunez.springbootmicroservice3apigateway.controller;

import com.mantunez.springbootmicroservice3apigateway.model.Tipo_Documento;
import com.mantunez.springbootmicroservice3apigateway.service.TipoDocumentoService;
import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tipoDocumento")
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @PostMapping("/register")
    public RespuestaDTO createTipoDoc(@RequestBody Tipo_Documento request) throws Exception {
        return tipoDocumentoService.register(request);
    }

    @GetMapping("/getTipoDocumento")
    public RespuestaDTO getListaTipoDocumento() throws Exception{
        List<Tipo_Documento> registros = tipoDocumentoService.getTipoDocumento();
        RespuestaDTO respuesta = new RespuestaDTO();
        if(registros!=null){
            respuesta.setMensaje("Registros recuperados con exito.");
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setData(registros);
        }else {
            respuesta.setMensaje("No se encontraron registros.");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
            Tipo_Documento[] documentoVacio = new Tipo_Documento[0];
            respuesta.setData(documentoVacio);
        }
        return respuesta;
    }

    @PostMapping("/updateTipoDocumento")
    public RespuestaDTO updateListaTipoDocumento(@RequestBody Tipo_Documento request) throws Exception{
        return tipoDocumentoService.updateTipoDocumento(request);
    }

    @PostMapping("/deleteTipoDocumento/{id}")
    public RespuestaDTO deleteTipoDocumento(@PathVariable Long id)throws Exception{
        return tipoDocumentoService.deleteTipoDocumento(id);
    }
}
