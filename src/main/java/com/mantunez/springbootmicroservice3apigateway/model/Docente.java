package com.mantunez.springbootmicroservice3apigateway.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "docente")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_docente")
    private String codigoDocente;

    @Column(name = "estado")
    private Boolean estado;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPersona", referencedColumnName = "id")
    private Persona persona;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idTipoContrato", referencedColumnName = "id")
    private TipoContrato tipoContrato;
}
