package com.mantunez.springbootmicroservice3apigateway.service.impl;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.AreaDocente;
import com.mantunez.springbootmicroservice3apigateway.repository.AreaDocenteRepository;
import com.mantunez.springbootmicroservice3apigateway.service.AreaDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AreaDocenteServiceImpl implements AreaDocenteService {

    private final static Logger LOG_MONITOREO = Logger.getLogger("com.mantunez.springbootmicroservice3apigateway.service.impl.AreaDocenteServiceImpl");
    @Autowired
    private AreaDocenteRepository areaDocenteRepository;

    @Override
    public RespuestaDTO saveAreaDocente(AreaDocente request){
        RespuestaDTO respuesta = new RespuestaDTO();
        try {
        AreaDocente existe = areaDocenteRepository.verificarExiste(request.getArea().getId(), request.getDocente().getId()).orElse(null);
        if(existe!=null){
            respuesta.setMensaje("Ya existe un registro con esos parametros");
            respuesta.setCodigo(String.valueOf(HttpStatus.FOUND.value()));
            respuesta.setData(existe);
        }else {
            AreaDocente registro = new AreaDocente();
            registro.setArea(request.getArea());
            registro.setEstado(true);
            registro.setDocente(request.getDocente());
            registro = areaDocenteRepository.save(registro);
            respuesta.setMensaje("Registrado Correctamente");
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setData(registro);
        }
        }catch (Exception e){
            LOG_MONITOREO.log(Level.SEVERE,e.getMessage());
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO getAreaDocente(){
        RespuestaDTO respuesta = new RespuestaDTO();
        try {
            List<AreaDocente> lista = areaDocenteRepository.getActivos();
            if(!lista.isEmpty()){
                respuesta.setMensaje("Registros recuperaods");
                respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                respuesta.setData(lista);
            }else {
                respuesta.setMensaje("No se encontraron registros");
                respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));

            }
        }catch (Exception e){
            LOG_MONITOREO.log(Level.SEVERE,e.getMessage());
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO updateAreaDocente(AreaDocente request)
    {
        RespuestaDTO respuesta = new RespuestaDTO();
        try {
            AreaDocente buscar = areaDocenteRepository.findById(request.getId()).orElse(null);
            if(buscar!=null){
                buscar.setDocente(request.getDocente());
                buscar.setArea(request.getArea());
                buscar.setEstado(request.getEstado());
                buscar = areaDocenteRepository.save(buscar);
                respuesta.setMensaje("Registro actualizado");
                respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                respuesta.setData(buscar);
            }else {
                respuesta.setMensaje("No se encontraron registros");
                respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));

            }
        }catch (Exception e){
            LOG_MONITOREO.log(Level.SEVERE,e.getMessage());
        }

        return respuesta;
    }

    @Override
    public RespuestaDTO deleteAreaDocente(Long id){
        RespuestaDTO respuesta  = new RespuestaDTO();
        try {
            AreaDocente existe = areaDocenteRepository.findById(id).orElse(null);
            if(existe!=null){
                existe.setEstado(false);
                existe = areaDocenteRepository.save(existe);
                respuesta.setMensaje("Registro eliminado");
                respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                respuesta.setData(existe);
            }else {
                respuesta.setMensaje("No se encontraron registros");
                respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));

            }
        }catch (Exception e){
            LOG_MONITOREO.log(Level.SEVERE,e.getMessage());
        }
        return respuesta;
    }
}
