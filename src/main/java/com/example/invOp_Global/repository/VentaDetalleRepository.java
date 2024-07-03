package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.VentaDetalle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaDetalleRepository extends BaseRepository<VentaDetalle,Long>{
    @Query(
            value= "SELECT * FROM `venta-detalles` where id_venta=:ventaId;",
            nativeQuery = true
    )
    List<VentaDetalle> findDetallesVenta(@Param("ventaId") Long ventaId);

}
