package com.ceiba.tiqueteparqueo.modelo.entidad;

import lombok.Getter;

import java.time.LocalDateTime;

import com.ceiba.tiqueteparqueo.modelo.TipoVehiculo;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarValido;

@Getter
public class TiqueteParqueo {

	private static final String FECHA_INGRESO_VACIA = "Se debe indicar la fecha de ingreso";
	private static final String DEBE_INGRESAR_VEHICULO_VALIDO = "Debe seleccionar un vehiculo valido";
	private static final String SE_DEBE_INGRESAR_LA_PLACA = "Se debe ingresar la placac del vehiculo";

	private Long id;
	private String placaVehiculo;
	private TipoVehiculo tipoVehiculo;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private Double valorAPagar;

	public TiqueteParqueo(Long id, String placaVehiculo, String tipoVehiculo, LocalDateTime fechaIngreso,
			LocalDateTime fechaSalida) {
		validarObligatorio(placaVehiculo, SE_DEBE_INGRESAR_LA_PLACA);
		validarObligatorio(fechaIngreso, FECHA_INGRESO_VACIA);
		validarValido(tipoVehiculo, TipoVehiculo.values(), DEBE_INGRESAR_VEHICULO_VALIDO);

		this.id = id;
		this.tipoVehiculo = TipoVehiculo.valueOf(tipoVehiculo);
		this.placaVehiculo = placaVehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
	}
}
