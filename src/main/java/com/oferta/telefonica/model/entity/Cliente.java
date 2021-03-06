package com.oferta.telefonica.model.entity;

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
@Table(name = "clientes")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(name = "name")
    private String name;

    @Column(name = "type_document")
    private String typeDocument;

    @Column(name = "number_document")
    private String numberDocument;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "customer_line",
//            joinColumns = @JoinColumn(name = "idCliente"),
//            inverseJoinColumns = @JoinColumn(name = "idLinea")
//    )
    @OneToMany(targetEntity = Linea.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id_cliente")
    private List<Linea> lineas;

    @NotNull
    @Column(name = "create_client")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createClient;

}
