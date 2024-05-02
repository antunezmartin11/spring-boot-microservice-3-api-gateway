package com.mantunez.springbootmicroservice3apigateway.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_alumno")
    private String codAlumno;

    @Column(name = "estado")
    private Boolean estado;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPersona", referencedColumnName = "id")
    private Persona persona;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idGrado", referencedColumnName = "id")
    private Grado grado;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idSeccion", referencedColumnName = "id")
    private Seccion seccion;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idResponsable", referencedColumnName = "id")
    private Responsable responsable;



}
