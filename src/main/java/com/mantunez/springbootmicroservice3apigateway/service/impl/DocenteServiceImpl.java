package com.mantunez.springbootmicroservice3apigateway.service.impl;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Alumno;
import com.mantunez.springbootmicroservice3apigateway.model.Docente;
import com.mantunez.springbootmicroservice3apigateway.model.Persona;
import com.mantunez.springbootmicroservice3apigateway.model.Responsable;
import com.mantunez.springbootmicroservice3apigateway.repository.AlumnoRepository;
import com.mantunez.springbootmicroservice3apigateway.repository.DocenteRepository;
import com.mantunez.springbootmicroservice3apigateway.repository.PersonaRepository;
import com.mantunez.springbootmicroservice3apigateway.repository.ResponsableRepository;
import com.mantunez.springbootmicroservice3apigateway.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteServiceImpl implements DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    private AlumnoRepository alumnoRepository;

    private ResponsableRepository responsableRepository;
    @Override
    public RespuestaDTO saveDocente(Docente request){
        Persona existePersona = personaRepository.findByNumeroDocumento(request.getPersona().getNumeroDocumento()).orElse(null);
        RespuestaDTO respuesta = new RespuestaDTO();
        if(existePersona!= null){
            Alumno esAlumno = alumnoRepository.findByIdPersona(existePersona.getId()).orElse(null);
            if(esAlumno!=null){
                respuesta.setMensaje("La persona fue registrado como alumno");
                respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST));
                respuesta.setData(esAlumno);
                return respuesta;
            }
            Responsable esResponsable  = responsableRepository.findByIdPersona(existePersona.getId()).orElse(null);
            if(esResponsable!=null){
                respuesta.setMensaje("La persona fue registrado como responsable");
                respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST));
                respuesta.setData(esAlumno);
                return respuesta;
            }
        }else {
            Docente nuevoDocente = new Docente();
            Persona registrarPersona = personaRepository.save(request.getPersona());
            nuevoDocente.setCodigoDocente(request.getCodigoDocente());
            nuevoDocente.setEstado(request.getEstado());
            nuevoDocente.setPersona(registrarPersona);
            nuevoDocente.setTipoContrato(request.getTipoContrato());
            nuevoDocente = docenteRepository.save(nuevoDocente);
            respuesta.setMensaje("Docente registrado correctamente");
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setData(nuevoDocente);
        }
        return  respuesta;
    }

    @Override
    public RespuestaDTO listarDocente(){
        RespuestaDTO respuesta = new RespuestaDTO();
        List<Docente> lista = docenteRepository.findAll();
        if(!lista.isEmpty()){
            respuesta.setMensaje("Registros recuperados");
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setData(lista);
        }else {
            respuesta.setMensaje("No se encontaron registros");
            respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));

        }
        return respuesta;
    }

    @Override
    public RespuestaDTO updateDocente(Docente request){
        RespuestaDTO respuesta = new RespuestaDTO();
        Docente existe= docenteRepository.findById(request.getId()).orElse(null);
        if(existe!=null){
            existe.setTipoContrato(request.getTipoContrato());
            existe.setCodigoDocente(request.getCodigoDocente());
            existe.setEstado(request.getEstado());
            existe.setPersona(request.getPersona());
            existe = docenteRepository.save(existe);
            respuesta.setMensaje("Actualizado Correctamente");
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setData(existe);
        }else {
            respuesta.setMensaje("No se encontaron registros");
            respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO deleteDocente(Long id){
        RespuestaDTO respuesta = new RespuestaDTO();
        Docente existeDocente = docenteRepository.findById(id).orElse(null);
        if(existeDocente!=null){
            existeDocente.setEstado(false);
            existeDocente = docenteRepository.save(existeDocente);
            respuesta.setMensaje("Eliminado Correctamente");
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setData(existeDocente);
        }else {
            respuesta.setMensaje("No se encontaron registros");
            respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
        return respuesta;
    }
}
