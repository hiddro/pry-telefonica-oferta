package com.oferta.telefonica.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "line")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Linea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nro_telefono")
    private String nroTelefono;

    @Column(name = "estado")
    private String estado;

    @Column(name = "type")
    private String type;

    @Column(name = "nombre_plan")
    private String nombrePlan;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "oferta_id")
    private List<Oferta> ofertas;
}
