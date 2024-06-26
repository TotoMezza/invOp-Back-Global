package com.example.invOp_Global.repository;

import com.example.invOp_Global.entities.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface VentaRepository extends BaseRepository<Venta, Long> {

    @Query(
            value = "SELECT * " +
                    "FROM ventas " +
                    "WHERE fecha_venta " +
                    "BETWEEN :fechaDesde AND :fechaHasta",
            nativeQuery = true
    )
    List<Venta> findVentasByFechas(@Param("fechaDesde") LocalDate fechaDesde, @Param("fechaHasta") LocalDate fechaHasta);

}

