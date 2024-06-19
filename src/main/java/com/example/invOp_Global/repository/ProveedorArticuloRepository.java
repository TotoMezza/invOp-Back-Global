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
                    "WHERE pa.articulo_id = :filtroArticulo",
            nativeQuery = true
    )
    List<ProveedorArticulo> findProveedoresByArticulo(@PathVariable("filtroArticulo") Long filtroArticulo);


    @Query(
            value = "SELECT DISTINCT a.nombre_articulo " +
                    "FROM articulos a " +
                    "JOIN proveedor_articulo pa ON pa.articulo_id = a.id " +
                    "WHERE pa.id_proveedor = :filtroProveedor",
            nativeQuery = true
    )
    List<ProveedorArticulo> findArticulosByProveedor(@Param("filtroProveedor") Long filtroProveedor);

}
