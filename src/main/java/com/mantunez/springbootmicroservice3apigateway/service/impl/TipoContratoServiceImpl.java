package com.mantunez.springbootmicroservice3apigateway.service.impl;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.TipoContrato;
import com.mantunez.springbootmicroservice3apigateway.repository.TipoContratoRepository;
import com.mantunez.springbootmicroservice3apigateway.service.TipoContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoContratoServiceImpl implements TipoContratoService {

    @Autowired
    private TipoContratoRepository tipoContratoRepository;

    @Override
    public RespuestaDTO saveTipoContrato(TipoContrato request){
        RespuestaDTO respuesta = new RespuestaDTO();
        TipoContrato  existe = tipoContratoRepository.findByNombre(request.getNombre());
        if(existe!=null){
            respuesta.setData(existe);
            respuesta.setMensaje("Ya existe un registro con esos parametros");
            respuesta.setCodigo(String.valueOf(HttpStatus.FOUND.value()));
        }else {
            TipoContrato registro = new TipoContrato();
            registro.setEstado(request.getEstado());
            registro.setNombre(request.getNombre());
            registro.setObservacion(request.getObservacion());

            registro = tipoContratoRepository.save(registro);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setData(registro);
            respuesta.setMensaje("Se registro correctamente");
        }

        return respuesta;
    }

    @Override
    public RespuestaDTO updateTipoContrato(TipoContrato request){

        RespuestaDTO respuesta = new RespuestaDTO();
        TipoContrato existe = tipoContratoRepository.findById(request.getId()).orElse(null);

        if(existe!=null){
            existe.setNombre(request.getNombre());
            existe.setObservacion(request.getObservacion());
            existe.setEstado(request.getEstado());
            existe = tipoContratoRepository.save(existe);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setMensaje("Registrado correctamente");
            respuesta.setData(existe);
        }else {
            respuesta.setMensaje("No se encontro el registro");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO listarTipoContrato(){
        RespuestaDTO respuesta = new RespuestaDTO();
        List<TipoContrato> lista = tipoContratoRepository.findAll();
        if(lista.isEmpty()){
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
            respuesta.setMensaje("No se encontraron registros");
        }else {
            respuesta.setMensaje("Registros recuperados");
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setData(lista);
        }
        return respuesta;
    }

    @Override
    public Boolean delete(Long id){
        TipoContrato existe = tipoContratoRepository.findById(id).orElse(null);
        if(existe!=null){
            existe.setEstado(false);

            existe = tipoContratoRepository.save(existe);
            return true;
        }

        return false;

    }
}
