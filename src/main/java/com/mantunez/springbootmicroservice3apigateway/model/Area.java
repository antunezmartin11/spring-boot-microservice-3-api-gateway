package com.mantunez.springbootmicroservice3apigateway.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;


    @Column(name = "horas")
    private Integer horas;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "eliminado")
    private boolean eliminado = false;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idGrado", referencedColumnName = "id")
    private Grado grado;

}
