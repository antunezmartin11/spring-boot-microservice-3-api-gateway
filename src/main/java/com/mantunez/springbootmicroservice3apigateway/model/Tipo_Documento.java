package com.mantunez.springbootmicroservice3apigateway.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tipo_documento")
public class Tipo_Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @Column(name="eliminado", nullable = false)
    private boolean eliminado = false;


}
