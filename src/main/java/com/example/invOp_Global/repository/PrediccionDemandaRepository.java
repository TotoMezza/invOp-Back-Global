package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.PrediccionDemanda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PrediccionDemandaRepository extends BaseRepository<PrediccionDemanda,Long>{
    @Query(
            value = "SELECT * " +
                    "FROM prediccion_demanda " +
                    "WHERE articulo_id = :articuloId ",
            nativeQuery = true
    )

    List<PrediccionDemanda> prediccionesPorArticulo(@PathVariable("articuloId") Long articuloId);

    @Query(
            value = "SELECT * " +
                    "FROM prediccion_demanda " +
                    "WHERE id_articulo = :articuloId " +
                    "AND YEAR(fecha_prediccion) = :anio "+
                    "AND MONTH(fecha_prediccion) = :mes "+
                    "LIMIT 1",
            nativeQuery = true
    )
    PrediccionDemanda prediccionPorArticuloAndFechas(@Param("articuloId") Long articuloId, @Param("anio") int anio, @Param("mes") int mes);
}
