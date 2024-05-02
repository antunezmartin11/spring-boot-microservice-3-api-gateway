package com.mantunez.springbootmicroservice3apigateway.service.impl;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Seccion;
import com.mantunez.springbootmicroservice3apigateway.repository.SeccionRepository;
import com.mantunez.springbootmicroservice3apigateway.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeccionServiceImpl implements SeccionService {

    @Autowired
    private SeccionRepository seccionRepository;


    @Override
    public RespuestaDTO saveSeccion(Seccion request){
        Seccion registro= seccionRepository.findByNombre(request.getNombre());
        RespuestaDTO respuesta = new RespuestaDTO();

        if(registro!=null){
            respuesta.setCodigo(String.valueOf(HttpStatus.FOUND));
            respuesta.setMensaje("Ya existe un registro con los mismos parametros.");
            respuesta.setData(registro);
        }else {
            Seccion nuevoRegistro = new Seccion();
            nuevoRegistro.setEstado(request.isEstado());
            nuevoRegistro.setNombre(request.getNombre());

            nuevoRegistro = seccionRepository.save(nuevoRegistro);
            respuesta.setData(nuevoRegistro);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK));
            respuesta.setMensaje("Registrado correctamente.");
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO getAllSeccion(){
        List<Seccion> lista = seccionRepository.getAllSeccionActivo();
        RespuestaDTO respuesta = new RespuestaDTO();
        if(lista!=null){
            respuesta.setMensaje("Registros recuperados con exito.");
            respuesta.setCodigo(String.valueOf(HttpStatus.OK));
            respuesta.setData(lista);
        }else {
            respuesta.setMensaje("No se encontraron registros.");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND));
            Seccion[] vacio = new Seccion[0];
            respuesta.setData(vacio);
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO updateSeccion(Seccion request){
        Optional<Seccion> getSeccion= seccionRepository.findById(request.getId());
        Seccion seccionActual = getSeccion.orElse(null);
        RespuestaDTO respuesta = new RespuestaDTO();
        if(seccionActual!=null){

            seccionActual.setNombre(request.getNombre());
            seccionActual.setEstado(request.isEstado());

            seccionActual = seccionRepository.save(seccionActual);
            respuesta.setData(seccionActual);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setMensaje("Seccion actualizado correctamente.");
        }else{
            respuesta.setMensaje("No se encontro el registro.");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO deleteSeccion(Long id){
        Optional<Seccion> seccionActual = seccionRepository.findById(id);
        Seccion registro = seccionActual.orElse(null);
        RespuestaDTO respuesta = new RespuestaDTO();
        if(registro!=null){
            registro.setEstado(false);
            seccionRepository.save(registro);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setMensaje("Registro eliminado correctamente.");
        }else {
            respuesta.setMensaje("No se encontro el registro");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
        }
        return respuesta;
    }

}
