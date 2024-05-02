package com.mantunez.springbootmicroservice3apigateway.service.impl;

import com.mantunez.springbootmicroservice3apigateway.dto.mapper.tipoDocumentoMapper;
import com.mantunez.springbootmicroservice3apigateway.dto.tipoDocumentoDTO;
import com.mantunez.springbootmicroservice3apigateway.model.Tipo_Documento;
import com.mantunez.springbootmicroservice3apigateway.service.TipoDocumentoService;
import com.mantunez.springbootmicroservice3apigateway.dto.RespuestaDTO;
import com.mantunez.springbootmicroservice3apigateway.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {


    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    public RespuestaDTO register(Tipo_Documento tipoDoc) throws Exception{

        Tipo_Documento registroTipoDoc = tipoDocumentoRepository.findByNombre(tipoDoc.getNombre());
        RespuestaDTO respuesta = new RespuestaDTO();
        try {
            if(registroTipoDoc!=null){
                //tipoDocumentoDTO datos = tipoDocumentoMapper.INSTANCE.convertirTipoDocumentoToTipoDocumentoDTO(registroTipoDoc);

                respuesta.setData(registroTipoDoc);
                respuesta.setCodigo(String.valueOf(HttpStatus.FOUND));
                respuesta.setMensaje("El documento ingresado ya esta registrado");
            }else {

                //Tipo_Documento requestTipoDocumento = tipoDocumentoMapper.INSTANCE.convertirTipoDocumentoDTOToTipoDocumento(tipoDoc);

                registroTipoDoc = tipoDocumentoRepository.save(tipoDoc);
                respuesta.setCodigo(String.valueOf(HttpStatus.CREATED.value()));
                respuesta.setData(registroTipoDoc);
                respuesta.setMensaje("Registrado correctamente");

            }
        }catch (Exception e){
            respuesta.setMensaje("Ocurrio un error al registrar el tipo de documento");
            respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
        return respuesta;
    }

    @Override
    public List<Tipo_Documento> getTipoDocumento() throws Exception{

        return tipoDocumentoRepository.getTipoDocumentoActivo();
    }

    @Override
    public RespuestaDTO updateTipoDocumento(Tipo_Documento request) throws Exception{
        Optional<Tipo_Documento>  registroActual = tipoDocumentoRepository.findById(request.getId());
        Tipo_Documento registro = registroActual.orElse(null);
        RespuestaDTO respuesta =  new RespuestaDTO();
        if(registro!=null){
            registro.setNombre(request.getNombre());
            registro.setAbreviatura(request.getAbreviatura());
            registro.setEstado(request.isEstado());

            registro = tipoDocumentoRepository.save(registro);
            respuesta.setData(registro);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setMensaje("Registro actualizado correctamente");
        }else {
            respuesta.setMensaje("No se encontro el registro");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));

        }
        return respuesta;
    }

    @Override
    public RespuestaDTO deleteTipoDocumento(Long id) throws Exception{
        Optional<Tipo_Documento>  registroActual = tipoDocumentoRepository.findById(id);
        Tipo_Documento registro = registroActual.orElse(null);
        RespuestaDTO respuesta =  new RespuestaDTO();
        if(registro!=null){
            registro.setEstado(false);
            tipoDocumentoRepository.save(registro);
            respuesta.setCodigo(String.valueOf(HttpStatus.OK.value()));
            respuesta.setMensaje("Registro eliminado");
        }else {
            respuesta.setMensaje("No se encontro el registro");
            respuesta.setCodigo(String.valueOf(HttpStatus.NOT_FOUND.value()));
        }
        return respuesta;
    }

}
