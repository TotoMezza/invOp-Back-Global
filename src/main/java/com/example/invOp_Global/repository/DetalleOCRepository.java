package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.DetalleOrdenCompra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleOCRepository extends BaseRepository<DetalleOrdenCompra,Long>{
    @Query(
            value = "SELECT * FROM inventario.ordencompradetalle as ocd where ocd.id_articulo=:articuloId ",
            nativeQuery = true
    )
    List<DetalleOrdenCompra> findDetalleOCByArticulo(@Param("articuloId") Long articuloId);
}
