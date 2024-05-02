package com.mantunez.springbootmicroservice3apigateway.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "area_docente")
public class AreaDocente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idArea", referencedColumnName = "id")
    private Area area;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idDocente", referencedColumnName = "id")
    private Docente docente;

    @Column(name = "estado")
    private Boolean estado;

}
