package com.mantunez.springbootmicroservice3apigateway.service.impl;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Persona;
import com.mantunez.springbootmicroservice3apigateway.model.Responsable;
import com.mantunez.springbootmicroservice3apigateway.repository.PersonaRepository;
import com.mantunez.springbootmicroservice3apigateway.repository.ResponsableRepository;
import com.mantunez.springbootmicroservice3apigateway.service.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponsableServiceImpl implements ResponsableService {

    @Autowired
    private ResponsableRepository responsableRepository;

    @Autowired
    private PersonaRepository personaRepository;


    @Override
    public RespuestaDTO saveResponsable(Responsable request){
        Persona buscarPersona = personaRepository.findByNumeroDocumento(request.getPersona().getNumeroDocumento()).orElse(null);
        RespuestaDTO respuesta = new RespuestaDTO();
        if(buscarPersona!=null){///Si hay registro
            Responsable existe = responsableRepository.findByIdPersona(buscarPersona.getId()).orElse(null);
            if(existe!=null){
                respuesta.setCodigo(String.valueOf(HttpStatus.FOUND.value()));
                respuesta.setData(buscarPersona);
                respuesta.setMensaje("Ya existe un responsable con los datos ingresados.");
            }else {
                Responsable nuevo = new Responsable();
                nuevo.setCodResponsable(request.getCodResponsable());
                nuevo.setEstado(request.getEstado());
                nuevo.setPersona(buscarPersona);
                nuevo = responsableRepository.save(nuevo);
                respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                respuesta.setData(nuevo);
                respuesta.setMensaje("Responsable registrado correctamente.");
            }

        }else {
            Responsable nuevo = new Responsable();
            nuevo.setCodResponsable(request.getCodResponsable());
            nuevo.setEstado(request.getEstado());
            nuevo.setPersona(request.getPersona());
            nuevo = responsableRepository.save(nuevo);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setData(nuevo);
            respuesta.setMensaje("Responsable registrado correctamente.");
        }
        return respuesta;
    }
}
