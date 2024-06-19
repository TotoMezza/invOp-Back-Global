package com.example.invOp_Global;

import com.example.invOp_Global.entities.*;
import com.example.invOp_Global.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.invOp_Global.enums.ModeloInventario.LOTE_FIJO;

@SpringBootApplication
public class InvOpGlobalApplication {
	@Autowired
	private ArticuloRepository articuloRepository;

	@Autowired
	private DemandaRepository demandaRepository;

	@Autowired
	private DetalleOCRepository detalleOCRepository;

	@Autowired
	private OrdenCompraRepository ordenCompraRepository;

	@Autowired
	private PrediccionDemandaRepository prediccionDemandaRepository;

	@Autowired
	private PrediccionRepository prediccionRepository;

	@Autowired
	private ProveedorArticuloRepository proveedorArticuloRepository;

	@Autowired
	private ProveedorRepository proveedorRepository;

	@Autowired
	private VentaDetalleRepository ventaDetalleRepository;

	@Autowired
	private VentaRepository ventaRepository;


	public static void main(String[] args) {
		SpringApplication.run(InvOpGlobalApplication.class, args);

		System.out.println("Hola, estoy andando bien");
	}

	@Bean
	CommandLineRunner init(){
		return  args -> {
			SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
			String fechaString = "2023-09-13";
			String fechaString2 = "2023-05-19";
			String fechaString3 = "2023-03-21";
			String fechaString4 = "2023-07-11";
			Date fecha = formatoFecha.parse(fechaString);
			Date fecha2 = formatoFecha.parse(fechaString2);
			Date fecha3 = formatoFecha.parse(fechaString3);
			Date fecha4 = formatoFecha.parse(fechaString4);

			Proveedor proveedor1= Proveedor.builder()
					.nombreProveedor("Samsgung")
			.build();

			Articulo articulo1= Articulo.builder()
				.nombre("Samsung S23")
				.precio(100.0)
				.modeloInventario(LOTE_FIJO)
			.build();

			ProveedorArticulo provArt1=ProveedorArticulo.builder()
				.tiempoDemora(2)
				.costoAlmacenamiento(5.0)
				.precioArticulo(500.00)
				.costoPedidoArticulo(80.0)
				.articulo(articulo1)
			.build();

			proveedor1.AgregarProveedorArticulo(provArt1);

			Venta venta1=Venta.builder()
				.fechaVenta(fecha)
			.build();

			VentaDetalle vd1=VentaDetalle.builder()
				.cantidadVenta(4)
				.articulo(articulo1)
			.build();
			vd1.setSubtotal();

			venta1.AgregarDetalleVenta(vd1);


			venta1.CalcularTotalVenta();
			venta1.CalcularTotalVenta();

			Demanda demanda1=Demanda.builder()
					.articulo(articulo1)
					.fechaDesde(fecha3)
					.fechaHasta(fecha4)
			.build();

			Venta venta2=Venta.builder()
				.fechaVenta(fecha)
			.build();

			VentaDetalle v2d1=VentaDetalle.builder()
					.cantidadVenta(6)
					.articulo(articulo1)
					.build();
			v2d1.setSubtotal();

			VentaDetalle v2d2=VentaDetalle.builder()
					.cantidadVenta(3)
					.articulo(articulo1)
					.build();
			v2d2.setSubtotal();

			venta2.AgregarDetalleVenta(v2d1);
			venta2.AgregarDetalleVenta(v2d2);

			venta2.CalcularTotalVenta();

			demanda1.agregarVenta(venta1);
			demanda1.agregarVenta(venta2);

			demanda1.CalcularTotalDemanda();

			demanda1.CalcularTotalDemanda();


			articulo1.calcularValores(demanda1, provArt1);

			articuloRepository.save(articulo1);
			/*ventaDetalleRepository.save(vd1);
			ventaRepository.save(venta1);


			proveedorArticuloRepository.save(provArt1);
			proveedorRepository.save(proveedor1);
		*/
		};
	}


}
