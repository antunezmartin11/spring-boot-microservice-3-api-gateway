package com.mantunez.springbootmicroservice3apigateway.service.impl;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Grado;
import com.mantunez.springbootmicroservice3apigateway.repository.GradoRepository;
import com.mantunez.springbootmicroservice3apigateway.service.GradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradoServiceImpl implements GradoService {

    @Autowired
    private GradoRepository gradoRepository;

    @Override
    public RespuestaDTO saveGrado(Grado request){
        Grado registro= gradoRepository.findByNombre(request.getNombre());
        RespuestaDTO respuesta = new RespuestaDTO();

        if(registro!=null){
            respuesta.setCodigo(String.valueOf(HttpStatus.FOUND));
            respuesta.setMensaje("Ya existe un registro con los mismos parametros.");
            respuesta.setData(registro);
        }else {
            Grado nuevoRegistro = new Grado();
            nuevoRegistro.setEstado(request.getEstado());
            nuevoRegistro.setNombre(request.getNombre());
            nuevoRegistro.setAbreviatura(request.getAbreviatura());
            nuevoRegistro = gradoRepository.save(nuevoRegistro);
            respuesta.setData(nuevoRegistro);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK));
            respuesta.setMensaje("Registrado correctamente.");
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO getAllGrado(){
        List<Grado> lista = gradoRepository.getAllGradoActivo();
        RespuestaDTO respuesta = new RespuestaDTO();
        if(lista!=null){
            respuesta.setMensaje("Registros recuperados con exito.");
            respuesta.setCodigo(String.valueOf(HttpStatus.OK));
            respuesta.setData(lista);
        }else {
            respuesta.setMensaje("No se encontraron registros.");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND));
            Grado[] vacio = new Grado[0];
            respuesta.setData(vacio);
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO updateGrado(Grado request){
        Optional<Grado> getGrado= gradoRepository.findById(request.getId());
        Grado gradoActual = getGrado.orElse(null);
        RespuestaDTO respuesta = new RespuestaDTO();
        if(gradoActual!=null){
            gradoActual.setAbreviatura(request.getAbreviatura());
            gradoActual.setNombre(request.getNombre());
            gradoActual.setEstado(request.getEstado());

            gradoActual = gradoRepository.save(gradoActual);
            respuesta.setData(gradoActual);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setMensaje("Grado actualizado correctamente.");
        }else{
            respuesta.setMensaje("No se encontr el registro.");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO deleteGrado(Long id){
        Optional<Grado> gradoActual = gradoRepository.findById(id);
        Grado registro = gradoActual.orElse(null);
        RespuestaDTO respuesta = new RespuestaDTO();
        if(registro!=null){
            registro.setEstado(false);
            gradoRepository.save(registro);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setMensaje("Registro eliminado correctamente.");
        }else {
            respuesta.setMensaje("No se encontro el registro");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
        }
        return respuesta;
    }
}
