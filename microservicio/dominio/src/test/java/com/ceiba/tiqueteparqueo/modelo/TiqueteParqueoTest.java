package com.ceiba.tiqueteparqueo.modelo;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;

import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.testdatabuilder.TiqueteParqueoTestDataBuilder;

public class TiqueteParqueoTest {
	
	@Test
	public void validarConstructor() {
		// Arrange - act
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder()
				.conFechaIngreso(LocalDateTime.of(2020, Month.JULY, 24, 12, 0)).conFechaSalida(LocalDateTime.of(2020, Month.JULY, 25, 12, 0)).conId(1L).build();
		// Assert
		Assert.assertEquals((Double)0.0, tiqueteParqueo.getValorAPagar());
		Assert.assertEquals("CARRO", tiqueteParqueo.getTipoVehiculo());
		Assert.assertEquals(LocalDateTime.of(2020, Month.JULY, 24, 12, 0), tiqueteParqueo.getFechaIngreso());
		Assert.assertEquals(LocalDateTime.of(2020, Month.JULY, 25, 12, 0), tiqueteParqueo.getFechaSalida());
	}

}
