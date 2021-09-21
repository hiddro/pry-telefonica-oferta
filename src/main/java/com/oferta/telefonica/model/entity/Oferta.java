package com.oferta.telefonica.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ofertas")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Oferta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_oferta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOferta;

    @Column(name = "codigo_oferta")
    private String codigoOferta;

    private String descripcion;

    @Column(name = "have_line")
    private String haveLine;

    @NotNull
    @Column(name = "start_oferta")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startOferta;

    @NotNull
    @Column(name = "end_oferta")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endOferta;
}
