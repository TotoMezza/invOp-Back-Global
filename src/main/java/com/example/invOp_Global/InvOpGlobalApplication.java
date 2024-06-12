package com.example.invOp_Global;

import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.Venta;
import com.example.invOp_Global.entities.VentaDetalle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvOpGlobalApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvOpGlobalApplication.class, args);

		Venta venta1 = new Venta();

		VentaDetalle vd1 = new VentaDetalle();
		VentaDetalle vd2 = new VentaDetalle();
		VentaDetalle vd3 = new VentaDetalle();

		Articulo a1 = new Articulo();
		Articulo a2 = new Articulo();
		Articulo a3 = new Articulo();

		a1.setNombre("Celular");
		a1.setPrecio(1000.30);
		a2.setNombre("Notebook");
		a2.setPrecio(2600.30);
		a3.setNombre("Tablet");
		a3.setPrecio(850.30);

		vd1.setCantidadVenta(2);
		vd1.setArticulo(a1);
		vd1.setSubtotal();

		vd2.setCantidadVenta(2);
		vd2.setArticulo(a2);
		vd2.setSubtotal();

		vd3.setCantidadVenta(1);
		vd3.setArticulo(a3);
		vd3.setSubtotal();

		venta1.AgregarDetalleVenta(vd1);
		venta1.AgregarDetalleVenta(vd2);
		venta1.AgregarDetalleVenta(vd3);

		try{
			venta1.CalcularTotalVenta();
		}catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}





	}

}
