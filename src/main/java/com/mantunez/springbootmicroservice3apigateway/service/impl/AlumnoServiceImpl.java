package com.mantunez.springbootmicroservice3apigateway.service.impl;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Alumno;
import com.mantunez.springbootmicroservice3apigateway.model.Persona;
import com.mantunez.springbootmicroservice3apigateway.model.Responsable;
import com.mantunez.springbootmicroservice3apigateway.repository.AlumnoRepository;
import com.mantunez.springbootmicroservice3apigateway.repository.PersonaRepository;
import com.mantunez.springbootmicroservice3apigateway.repository.ResponsableRepository;
import com.mantunez.springbootmicroservice3apigateway.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private ResponsableRepository responsableRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public RespuestaDTO saveAlumno(Alumno request){
        Persona existePersona = personaRepository.findByNumeroDocumento(request.getPersona().getNumeroDocumento()).orElse(null);
        RespuestaDTO respuesta = new RespuestaDTO();
        if(existePersona!=null){//Existe una persona registra con ese numero de documento
            //Responsable existeResponsable = responsableRepository.findByIdPersona(existePersona.getId()).orElse(null);
            Alumno existeAlumno = alumnoRepository.findByIdPersona(existePersona.getId()).orElse(null);

            if(existeAlumno!=null){//Si esa persona se registro como alumno
                respuesta.setMensaje("Ya existe un alumno registrado con este numero de documento");
                respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST));
                respuesta.setData(existeAlumno);
                return respuesta;
            }
           /* Alumno nuevoRegistro = new Alumno();
            nuevoRegistro.setCodAlumno("A001");
            nuevoRegistro.setGrado(request.getGrado());
            nuevoRegistro.setSeccion(request.getSeccion());
            nuevoRegistro.setPersona(existePersona);
            nuevoRegistro.setResponsable(request.getResponsable());
            nuevoRegistro = alumnoRepository.save(nuevoRegistro);
            respuesta.setMensaje("Alumno Registrado");
            respuesta.setCodigo(String.valueOf(HttpStatus.OK));
            respuesta.setData(nuevoRegistro);*/
        }else {
            Alumno nuevoAlumno = new Alumno();
            nuevoAlumno = request;
            Persona registrarPersona = personaRepository.save(request.getPersona());
            Responsable existeResponsable = responsableRepository.findByIdPersona(request.getResponsable().getPersona().getId()).orElse(null);

            if(existeResponsable!=null){//Si ya esta registrado el responsable se le asigna al objeto alumno
                nuevoAlumno.setResponsable(existeResponsable);
            }else {
                Responsable registrarResponsable = new Responsable();
                registrarResponsable.setCodResponsable(request.getResponsable().getCodResponsable());
                registrarResponsable.setEstado(request.getResponsable().getEstado());
                registrarResponsable.setPersona(request.getResponsable().getPersona());

                registrarResponsable = responsableRepository.save(registrarResponsable);
                nuevoAlumno.setResponsable(registrarResponsable);
            }

            nuevoAlumno.setPersona(registrarPersona);
            nuevoAlumno = alumnoRepository.save(request);
            respuesta.setMensaje("Alumno registrado");
            respuesta.setCodigo(String.valueOf(HttpStatus.OK));
            respuesta.setData(nuevoAlumno);
        }

        return respuesta;
    }
}
