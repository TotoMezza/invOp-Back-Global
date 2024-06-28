package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.ProveedorArticulo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ProveedorArticuloRepository extends BaseRepository<ProveedorArticulo,Long>{



    @Query(
            value = "SELECT * " +
                    "FROM proveedor_articulo pa " +
                    "WHERE pa.id_articulo = :articuloId",
            nativeQuery = true
    )
    List<ProveedorArticulo> findProveedoresByArticulo(@PathVariable("articuloId") Long articuloId);


    @Query(
            value = "SELECT DISTINCT a.nombre_articulo " +
                    "FROM articulos a " +
                    "JOIN proveedor_articulo pa ON pa.articulo_id = a.id " +
                    "WHERE pa.id_proveedor = : proveedor_id",
            nativeQuery = true
    )
    List<ProveedorArticulo> findArticulosByProveedor(@Param("proveedor_id") Long proveedor_id);

    @Query(value = "SELECT * FROM proveedor_articulo as pa " +
                    "WHERE pa.id_proveedor = :proveedorId AND pa.id_articulo = :articuloId;", nativeQuery = true
    )
    ProveedorArticulo findProveedorArticuloByProveedorAndArticulo(@Param(value = "proveedorId") Long proveedorId, @Param(value = "articuloId") Long articuloId);

}
