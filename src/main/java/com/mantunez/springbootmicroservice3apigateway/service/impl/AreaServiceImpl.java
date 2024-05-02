package com.mantunez.springbootmicroservice3apigateway.service.impl;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Area;
import com.mantunez.springbootmicroservice3apigateway.repository.AreaRepository;
import com.mantunez.springbootmicroservice3apigateway.repository.GradoRepository;
import com.mantunez.springbootmicroservice3apigateway.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private GradoRepository gradoRepository;

    @Override
    public RespuestaDTO saveArea(Area request){
        Area existe = areaRepository.findByNombre(request.getNombre());
        RespuestaDTO respuesta = new RespuestaDTO();
        if(existe!=null){
            Area existeAreaGreado = areaRepository.getAreaGrado(request.getNombre(),request.getGrado().getId());
            if(existeAreaGreado!=null){
                respuesta.setCodigo(String.valueOf(HttpStatus.FOUND.value()));
                respuesta.setMensaje("Ya se encuentra registrada un area con esos parametros");
                respuesta.setData(existe);
            }else {
                Area registro = new Area();

                registro.setDescripcion(request.getDescripcion());
                registro.setNombre(request.getNombre());
                registro.setHoras(request.getHoras());
                registro.setEstado(request.getEstado());
                registro.setGrado(request.getGrado());
                registro = areaRepository.save(registro);
                respuesta.setData(registro);
                respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                respuesta.setMensaje("Area registrada correctamente");
            }
        }else {
            Area existeAreaGreado = areaRepository.getAreaGrado(request.getNombre(),request.getGrado().getId());
            if(existeAreaGreado!=null){
                respuesta.setCodigo(String.valueOf(HttpStatus.FOUND.value()));
                respuesta.setMensaje("Ya se encuentra registrada un area con esos parametros");
                respuesta.setData(existe);
            }else {
                Area registro = new Area();

                registro.setDescripcion(request.getDescripcion());
                registro.setNombre(request.getNombre());
                registro.setHoras(request.getHoras());
                registro.setEstado(request.getEstado());
                registro.setGrado(request.getGrado());
                registro = areaRepository.save(registro);
                respuesta.setData(registro);
                respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                respuesta.setMensaje("Area registrada correctamente");
            }

        }
        return respuesta;
    }

    @Override
    public RespuestaDTO listaArea(){
      RespuestaDTO respuesta = new RespuestaDTO();
      List<Area> lista = areaRepository.getAllArea();
      if(lista!=null){
        respuesta.setMensaje("Registro recuperados");
        respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
        respuesta.setData(lista);
      }else {
          respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
          respuesta.setMensaje("No se encontraron registros");
      }
      return respuesta;
    }

    @Override
    public RespuestaDTO updateArea(Area request){
        RespuestaDTO respuesta = new RespuestaDTO();
        Area existe= areaRepository.findById(request.getId()).orElse(null);
        if(existe!=null){
            existe.setEstado(request.getEstado());
            existe.setHoras(request.getHoras());
            existe.setNombre(request.getNombre());
            existe.setDescripcion(request.getDescripcion());
            existe.setGrado(request.getGrado());
            existe = areaRepository.save(existe);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setMensaje("Registro actualizado");
            respuesta.setData(existe);
        }else {
            respuesta.setMensaje("No se encontro ningun registro");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO deleteArea(Long id){
        RespuestaDTO respuesta = new RespuestaDTO();
        Area existe= areaRepository.findById(id).orElse(null);
        if(existe!=null){
            existe.setEstado(false);
            existe = areaRepository.save(existe);
            respuesta.setMensaje("Eliminado correctamente");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
            respuesta.setData(true);
        }else {
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
            respuesta.setMensaje("No se encontro ningun registro");
        }
        return respuesta;
    }

}
