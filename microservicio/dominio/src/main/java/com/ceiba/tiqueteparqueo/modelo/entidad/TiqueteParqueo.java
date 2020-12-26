package com.ceiba.tiqueteparqueo.modelo.entidad;

import lombok.Getter;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import com.ceiba.tiqueteparqueo.modelo.TipoVehiculo;
import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;

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
	 * Asigna el valor a pagar del tiquete
	 * 
	 * @param validadorFechaFestivo validadorFechaFestivo api para validar si una
	 *                              fecha es festivo en calendario Colombiano.
	 * @param tarifario             map de tarifas del parqueadero.
	 * @throws IOException 
	 */
	public void asignarValorPagar(ApiValidadorFechaFestivo validadorFechaFestivo, Map<String, Double> tarifario) throws IOException {
		// La salida se genera una sola vez, cuando la fecha de salida se haya asignado
		// y no haya valor total
		if (fechaSalida != null && valorAPagar == null) {
			this.valorAPagar = calcularTarifaNormal(tarifario.get(tipoVehiculo));

			if (fechaSalida.getDayOfWeek() != DayOfWeek.SATURDAY && fechaSalida.getDayOfWeek() != DayOfWeek.SUNDAY
					&& (fechaSalida.getHour() >= 23 || fechaSalida.getHour() <= 5)) {
				this.valorAPagar += tarifario.get("Recargo_Nocturno");
			}

			if (fechaIngreso.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0
					|| fechaIngreso.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0) {
				this.valorAPagar += (this.valorAPagar * 0.20);
			} else if (validadorFechaFestivo.esFestivo(fechaIngreso)) {
				this.valorAPagar = this.valorAPagar / 2;
			}
		}
	}

	/**
	 * Calcula la tarifa base del tiquete
	 * 
	 * @return tarifa base
	 */
	private double calcularTarifaNormal(double precioHora) {
		long hours = fechaIngreso.getMinute() == fechaSalida.getMinute() ? 0 : 1;
		hours += ChronoUnit.HOURS.between(fechaIngreso, fechaSalida);

		return hours * precioHora;
	}
}
