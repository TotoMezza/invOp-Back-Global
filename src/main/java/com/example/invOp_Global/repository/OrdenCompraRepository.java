package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.OrdenCompra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdenCompraRepository extends BaseRepository<OrdenCompra,Long>{

    @Query(
            value = "SELECT * FROM ordenes_compra WHERE estado_orden_compra LIKE %:filtroEstado%",
            nativeQuery = true
    )
    List<OrdenCompra> findOrdenCompraByEstado(@Param("filtroEstado") String filtroEstado);
}
