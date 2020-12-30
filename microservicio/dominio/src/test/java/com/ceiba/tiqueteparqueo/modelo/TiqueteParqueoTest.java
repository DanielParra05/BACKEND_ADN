package com.ceiba.tiqueteparqueo.modelo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;
import com.ceiba.tiqueteparqueo.servicio.testdatabuilder.TiqueteParqueoTestDataBuilder;

public class TiqueteParqueoTest {

	@Test
	public void validarValorAPagarConRecargoNocturno() throws IOException {
		// Arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder()
				.conFechaIngreso(LocalDateTime.of(2020, Month.JULY, 21, 18, 20))
				.conFechaSalida(LocalDateTime.of(2020, Month.JULY, 21, 23, 30)).build();
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		Mockito.when(validadorFechaFestivo.esFestivo(Mockito.anyObject())).thenReturn(false);
		// Act
		tiqueteParqueo.asignarValorPagar(validadorFechaFestivo, generarTarifario());
		// Assert
		Assert.assertEquals((Double) 19000.0, tiqueteParqueo.getValorAPagar());
	}

	@Test
	public void validarValorAPagarConFechaIngresoFestivo() throws IOException {
		// Arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder()
				.conFechaIngreso(LocalDateTime.of(2020, Month.JULY, 20, 12, 0))
				.conFechaSalida(LocalDateTime.of(2020, Month.JULY, 20, 16, 20)).build();
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		Mockito.when(validadorFechaFestivo.esFestivo(Mockito.anyObject())).thenReturn(true);
		// Act
		tiqueteParqueo.asignarValorPagar(validadorFechaFestivo, generarTarifario());
		// Assert
		Assert.assertEquals((Double) 6250.0, tiqueteParqueo.getValorAPagar());
	}

	@Test
	public void validarValorAPagarConFechaIngresoFinDeSemana() throws IOException {
		// Arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder()
				.conFechaIngreso(LocalDateTime.of(2020, Month.JULY, 19, 12, 0))
				.conFechaSalida(LocalDateTime.of(2020, Month.JULY, 19, 15, 0)).build();
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		Mockito.when(validadorFechaFestivo.esFestivo(Mockito.anyObject())).thenReturn(false);
		// Act
		tiqueteParqueo.asignarValorPagar(validadorFechaFestivo, generarTarifario());
		// Assert
		Assert.assertEquals((Double) 9000.0, tiqueteParqueo.getValorAPagar());
	}

	@Test
	public void validarValorAPagarEnLimpio() throws IOException {
		// Arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder()
				.conFechaIngreso(LocalDateTime.of(2020, Month.JULY, 24, 12, 0))
				.conFechaSalida(LocalDateTime.of(2020, Month.JULY, 24, 15, 0)).build();
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		Mockito.when(validadorFechaFestivo.esFestivo(Mockito.anyObject())).thenReturn(false);
		// Act
		tiqueteParqueo.asignarValorPagar(validadorFechaFestivo, generarTarifario());
		// Assert
		Assert.assertEquals((Double) 7500.0, tiqueteParqueo.getValorAPagar());
	}
	
	@Test
	public void validarConstructor() {
		// Arrange - act
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder()
				.conFechaIngreso(LocalDateTime.of(2020, Month.JULY, 24, 12, 0)).conFechaSalida(LocalDateTime.of(2020, Month.JULY, 25, 12, 0)).build();
		// Assert
		Assert.assertEquals((Double)0.0, tiqueteParqueo.getValorAPagar());
		Assert.assertEquals("CARRO", tiqueteParqueo.getTipoVehiculo());
		Assert.assertEquals(LocalDateTime.of(2020, Month.JULY, 24, 12, 0), tiqueteParqueo.getFechaIngreso());
		Assert.assertEquals(LocalDateTime.of(2020, Month.JULY, 25, 12, 0), tiqueteParqueo.getFechaSalida());
	}

	private Map<String, Double> generarTarifario() {
		return new HashMap<String, Double>() {
			{
				put("Recargo_Nocturno", 4000.0);
				put("CARRO", 2500.0);
			}
		};
	}

}
