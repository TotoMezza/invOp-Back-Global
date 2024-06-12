package com.example.invOp_Global.entities;

import com.example.invOp_Global.enums.TipoPrediccion;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "prediccion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Prediccion extends EntidadBase{
    @NotNull
    private Date fechaPrediccion;

    @NotNull
    private int periodoPrediccion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Articulo")
    private Articulo articulo;

    @Column(name = "error_Medicion")
    private float errorMedicion;

    @NotNull
    @Column(name = "tipo_prediccion")
    @Enumerated(EnumType.STRING)
    private TipoPrediccion tipoPrediccion;

    @OneToMany
    @JoinColumn(name = "id_prediccion_demanda")
    @Builder.Default
    private List<PrediccionDemanda> prediccionDemanda  = new ArrayList<>();

}