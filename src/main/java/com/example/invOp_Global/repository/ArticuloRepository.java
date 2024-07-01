package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.VentaDetalle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends BaseRepository<Articulo,Long>{

    @Modifying
    @Transactional
    @Query(
            value = "DELETE " +
                    "FROM demanda " +
                    "WHERE id_articulo = :idArticulo",
            nativeQuery = true
    )
    int deleteDemandasHistoricasPorArticulo(@Param("idArticulo") Long idArticulo);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE " +
                    "FROM ordencompradetalle " +
                    "WHERE id_articulo = :idArticulo ",
            nativeQuery = true
    )
    int deleteDetallesPorArticulo(@Param("idArticulo") Long idArticulo);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE " +
                    "FROM prediccion_demanda " +
                    "WHERE id_Articulo = :idArticulo ",
            nativeQuery = true
    )
    int deletePrediccionesByArticulo(@Param("idArticulo") Long idArticulo);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE " +
                    "FROM proveedor_articulo  " +
                    "WHERE id_articulo = :idArticulo",
            nativeQuery = true
    )
    int deleteProveedorArticuloByArticulo(@Param("idArticulo") Long idArticulo);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM ordenes_compra oc " +
                    "WHERE id IN ( SELECT oc.id FROM ordenes_compra oc JOIN detalles_orden_compra doc ON oc.id = doc.id_orden_compra WHERE doc.id_articulo = :idArticulo",
            nativeQuery = true
    )
    void   deleteOc(@Param("idArticulo") Long idArticulo);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE " +
                    "FROM `venta-detalles` " +
                    "WHERE id_Articulo = :idArticulo ",
            nativeQuery = true
    )
    int deleteDetallesByVenta(@Param("idArticulo") Long idArticulo);

    @Modifying
    @Transactional
    @Query(
            value = "SELECT * FROM `venta-detalles` vd WHERE vd.id_Articulo = :idArticulo",
            nativeQuery = true
    )
    List<VentaDetalle> findDetalleVentaArticulo(@Param("idArticulo") Long idArticulo);





}
