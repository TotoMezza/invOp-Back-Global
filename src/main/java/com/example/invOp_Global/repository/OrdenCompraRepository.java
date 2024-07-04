package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.DetalleOrdenCompra;
import com.example.invOp_Global.entities.OrdenCompra;
import com.example.invOp_Global.entities.VentaDetalle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends BaseRepository<OrdenCompra,Long> {

    @Query(
            value = "SELECT * FROM ordenes_compra WHERE estado_oc LIKE %:estado%",
            nativeQuery = true
    )
    List<OrdenCompra> findOrdenCompraByEstado(@Param("estado") String estado);

    @Query(
            value = "SELECT oc.* " +
                    "FROM ordencompra as oc " +
                    "INNER JOIN ordencompradetalle ocd ON oc.id = ocd.id_orden_compra " +
                    "WHERE ocd.id_articulo = :articuloId AND oc.estado_oc LIKE %:estado% ",
            nativeQuery = true
    )
    List<OrdenCompra> findOrdenCompraByEstadoAndArticulo(@Param("estado")String estado, @Param("articuloId") Long articuloId);
}
