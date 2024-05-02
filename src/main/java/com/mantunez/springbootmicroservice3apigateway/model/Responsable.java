package com.mantunez.springbootmicroservice3apigateway.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "responsable")
public class Responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_responsable")
    private String codResponsable;

    @Column(name = "estado")
    private Boolean estado;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPersona", referencedColumnName = "id")
    private Persona persona;
}
