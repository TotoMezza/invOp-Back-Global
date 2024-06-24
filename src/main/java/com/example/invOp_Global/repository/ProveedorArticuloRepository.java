package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.ProveedorArticulo;
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
                    "WHERE pa.id_articulo = :articulo_id",
            nativeQuery = true
    )
    List<ProveedorArticulo> findProveedoresByArticulo(@PathVariable("articulo_id") Long articulo_id);


    @Query(
            value = "SELECT DISTINCT a.nombre_articulo " +
                    "FROM articulos a " +
                    "JOIN proveedor_articulo pa ON pa.articulo_id = a.id " +
                    "WHERE pa.id_proveedor = : proveedor_id",
            nativeQuery = true
    )
    List<ProveedorArticulo> findArticulosByProveedor(@Param("proveedor_id") Long proveedor_id);

    @Query(value = "SELECT pa FROM ProveedorArticulo as pa " +
                    "WHERE pa.id_proveedor = :proveedor_id AND pa.id_articulo = :articulo_id;", nativeQuery = true
    )
    ProveedorArticulo findProveedorArticuloByProveedorAndArticulo(@Param(value = "proveedor_id") Long proveedor_id, @Param(value = "articulo_id") Long articulo_id);
}
