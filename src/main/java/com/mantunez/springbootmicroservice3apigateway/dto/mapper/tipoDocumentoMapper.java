package com.mantunez.springbootmicroservice3apigateway.dto.mapper;

import com.mantunez.springbootmicroservice3apigateway.model.Tipo_Documento;
import com.mantunez.springbootmicroservice3apigateway.dto.tipoDocumentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface tipoDocumentoMapper {

    tipoDocumentoMapper INSTANCE  = Mappers.getMapper(tipoDocumentoMapper.class);

    /*@Mappings({
            @Mapping(target = "estado", expression = "java(tipoDocumento.getEstado() ? \"Activo\":\"Inactivo\")")
    })*/
    tipoDocumentoDTO convertirTipoDocumentoToTipoDocumentoDTO(Tipo_Documento tipoDocumento);

   /* @Mappings({
            @Mapping(target = "estado",expression = "java(\"Activo\".equals(tipoDocumentoDTO.getEstado()))")
    })*/
    Tipo_Documento convertirTipoDocumentoDTOToTipoDocumento(tipoDocumentoDTO tipoDocumentoDTO);
}
