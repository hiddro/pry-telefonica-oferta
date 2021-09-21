package com.oferta.telefonica.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lineas")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Linea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_linea")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLinea;

    @Column(name = "nro_telefono")
    private String nroTelefono;

    @Column(name = "estado")
    private String estado;

    @Column(name = "type")
    private String type;

    @Column(name = "nombre_plan")
    private String nombrePlan;

    @Column(name = "have_client")
    private String haveClient;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "line_offer",
//            joinColumns = @JoinColumn(name = "idLinea"),
//            inverseJoinColumns = @JoinColumn(name = "idOferta")
//    )
    @OneToMany(targetEntity = Oferta.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "linea_id", referencedColumnName = "id_linea")
    private List<Oferta> ofertas = new ArrayList<>();

    @NotNull
    @Column(name = "create_line")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createLine;
}
