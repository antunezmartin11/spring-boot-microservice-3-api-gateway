package com.mantunez.springbootmicroservice3apigateway.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "administrativo")
public class Administrativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPersona", referencedColumnName = "id")
    private Persona persona;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idTipoDocumento", referencedColumnName = "id")
    private Tipo_Documento tipoDocumento;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "estado")
    private Boolean estado;
}
