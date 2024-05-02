package com.mantunez.springbootmicroservice3apigateway.service.impl;

import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.model.*;
import com.mantunez.springbootmicroservice3apigateway.repository.*;
import com.mantunez.springbootmicroservice3apigateway.service.AdmnistrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AdministrativoServiceImpl implements AdmnistrativoService {


    @Autowired
    private AdministrativoRepository administrativoRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ResponsableRepository responsableRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    private final static Logger LOG_MONITOREO = Logger.getLogger(AdministrativoServiceImpl.class.getName());
    @Override
    public RespuestaDTO saveAdministrativo(Administrativo request){
        RespuestaDTO respuesta = new RespuestaDTO();

        try {
            Persona existePersona = personaRepository.findByNumeroDocumento(request.getPersona().getNumeroDocumento()).orElse(null);
            Responsable existeResponsable = responsableRepository.findByIdPersona(request.getPersona().getId()).orElse(null);
            Alumno existeAlumno = alumnoRepository.findByIdPersona(request.getPersona().getId()).orElse(null);
            Docente existeDocente = docenteRepository.findByIdPersona(request.getPersona().getId()).orElse(null);
            if(existePersona!=null){
                if(existeDocente!=null){
                    respuesta.setMensaje("La  persona ya fue registrada");
                    respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                    respuesta.setData(existeDocente);

                    return respuesta;
                }
                if(existeResponsable!=null){
                    respuesta.setMensaje("La  persona ya fue registrada");
                    respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                    respuesta.setData(existeResponsable);

                    return respuesta;
                }
                if(existeAlumno!=null){
                    respuesta.setMensaje("La  persona ya fue registrada");
                    respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                    respuesta.setData(existeAlumno);

                    return respuesta;
                }
                Administrativo registro = new Administrativo();
                registro.setTipoDocumento(request.getTipoDocumento());
                registro.setPersona(existePersona);
                registro.setEstado(true);
                registro.setCargo(request.getCargo());
                registro = administrativoRepository.save(registro);
                respuesta.setMensaje("Registrado correctamente");
                respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                respuesta.setData(registro);
            }else {
                Persona registro = new Persona();
                registro = request.getPersona();
                registro = personaRepository.save(registro);
                Administrativo nuevo = new Administrativo();
                nuevo.setCargo(request.getCargo());
                nuevo.setEstado(true);
                nuevo.setPersona(registro);
                nuevo.setTipoDocumento(request.getTipoDocumento());
                nuevo = administrativoRepository.save(nuevo);
                respuesta.setMensaje("Registrado correctamente");
                respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                respuesta.setData(nuevo);
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO getAllAdministrativo(){
        RespuestaDTO respuesta = new RespuestaDTO();
        try {
            List<Administrativo> lista = administrativoRepository.getAllAdministrativoActivo();
            if(lista.isEmpty()){
                respuesta.setMensaje("No se encontraron registros");
                respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));

            }else {
                respuesta.setMensaje("Registrado recuperados");
                respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                respuesta.setData(lista);
            }
            LOG_MONITOREO.log(Level.INFO,"Fin de proceso de listar admnistrativos");
        }catch (Exception e){
            LOG_MONITOREO.log(Level.SEVERE,e.getMessage());
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO updateAdminsitrativo(Administrativo request){
        RespuestaDTO respuesta = new RespuestaDTO();
        try {
            Administrativo existe = administrativoRepository.findById(request.getId()).orElse(null);
            if(existe!=null){
                LOG_MONITOREO.log(Level.INFO,"Inicio proceso de actualizacion de administrativo");
                existe.setCargo(request.getCargo());
                existe.setEstado(request.getEstado());
                existe.setPersona(request.getPersona());
                existe.setTipoDocumento(request.getTipoDocumento());
                LOG_MONITOREO.log(Level.INFO,"Objeto a actualizar: "+existe);
                existe = administrativoRepository.save(existe);
                respuesta.setMensaje("Actualizado correctamente");
                respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
                respuesta.setData(existe);
                LOG_MONITOREO.log(Level.INFO,"Fin de proceso de actualizar administrativo");

            }else {
                respuesta.setMensaje("No se encontraron registros");
                respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }catch (Exception e){
            LOG_MONITOREO.log(Level.SEVERE,e.getMessage());
        }
        return respuesta;
    }

    @Override
    public RespuestaDTO deleteAdministrativo(Long id){
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        try {
            Administrativo existe = administrativoRepository.findById(id).orElse(null);
            if(existe!=null){
                LOG_MONITOREO.log(Level.INFO,"Inicio proceso de eliminacion de administrativo");
                existe.setEstado(false);
                existe = administrativoRepository.save(existe);
                respuestaDTO.setMensaje("Eliminado correctamente");
                respuestaDTO.setCodigo(String.valueOf(HttpStatus.OK.value()));
                respuestaDTO.setData(true);
                LOG_MONITOREO.log(Level.INFO,"Fin de proceso de eliminacion de administrativo");
            }else {
                respuestaDTO.setMensaje("No se encontraron registros");
                respuestaDTO.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
            }

            }catch (Exception e){
            LOG_MONITOREO.log(Level.SEVERE,e.getMessage());
        }
        return respuestaDTO;
    }
}
