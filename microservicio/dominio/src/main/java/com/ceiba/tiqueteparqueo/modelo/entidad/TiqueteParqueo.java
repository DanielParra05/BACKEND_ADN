package com.ceiba.tiqueteparqueo.modelo.entidad;

import lombok.Getter;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
	private String tipoVehiculo;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private Double valorAPagar;

	public TiqueteParqueo(Long id, String placaVehiculo, String tipoVehiculo, LocalDateTime fechaIngreso,
			LocalDateTime fechaSalida, Double valorAPagar) {
		validarObligatorio(placaVehiculo, SE_DEBE_INGRESAR_LA_PLACA);
		validarObligatorio(fechaIngreso, FECHA_INGRESO_VACIA);
		validarValido(tipoVehiculo, TipoVehiculo.values(), DEBE_INGRESAR_VEHICULO_VALIDO);

		this.id = id;
		this.tipoVehiculo = tipoVehiculo;
		this.placaVehiculo = placaVehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorAPagar = valorAPagar;
	}

	/**
	 * Calcula la tarifa base del tiquete
	 * 
	 * @return tarifa base
	 */
	public void calcularTarifaNormal(double precioHora) {
		long hours = (fechaIngreso.getMinute() == fechaSalida.getMinute())
				&& fechaIngreso.getHour() != fechaSalida.getHour() ? 0 : 1;
		hours += ChronoUnit.HOURS.between(fechaIngreso, fechaSalida);

		this.valorAPagar = hours * precioHora;
	}
	
	/**
	 * Adiciona un valor fijo al valor a pagar del tiquete
	 * @param adicion
	 */
	public void aplicarAdicionAlPago(double adicion) {
		this.valorAPagar = this.valorAPagar + adicion;
	}

	/**
	 * Adiciona un porcentaje al valor a pagar del tiquete
	 * @param porcentajeDescuento
	 */
	public void aplicarDescuentoPorcentualAlPago(double porcentajeDescuento) {
		this.valorAPagar = (this.valorAPagar - (this.valorAPagar * porcentajeDescuento));
	}
	
	/**
	 * Descuenta un porcentaje al valor a pagar del tiquete
	 * @param porcentajeAdicion
	 */
	public void aplicarAdicionPorcentualAlPago(double porcentajeAdicion) {
		this.valorAPagar = (this.valorAPagar + (this.valorAPagar * porcentajeAdicion));
	}

}
