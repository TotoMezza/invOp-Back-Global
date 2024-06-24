package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.PrediccionDemanda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PrediccionDemandaRepository extends BaseRepository<PrediccionDemanda,Long>{
    @Query(
            value = "SELECT * " +
                    "FROM prediccion_demanda " +
                    "WHERE articulo_id = :filtroArticulo ",
            nativeQuery = true
    )

    List<PrediccionDemanda> prediccionesPorArticulo(@PathVariable("filtroArticulo") Long filtroArticulo);
}
