package com.ceiba.tiqueteparqueo.modelo;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;

import com.ceiba.tiqueteparqueo.modelo.dto.DtoTiqueteParqueo;

public class DtoTiqueteParqueoTest {

	@Test
	public void validarCreacionDtoTiqueteParqueo() {
		// arrange - act
		DtoTiqueteParqueo dtoTiqueteParqueo = new DtoTiqueteParqueo(1L, "GKK550", "Carro", LocalDateTime.of(2020, Month.SEPTEMBER, 5,0,0,0),
				LocalDateTime.of(2020, Month.SEPTEMBER, 5,0,0,0), 0.0);
		// assert
		Assert.assertNotNull(dtoTiqueteParqueo);
		Assert.assertEquals((Long)1L, dtoTiqueteParqueo.getId());
		Assert.assertEquals("GKK550", dtoTiqueteParqueo.getPlacaVehiculo());
		Assert.assertEquals("Carro", dtoTiqueteParqueo.getTipoVehiculo());
		Assert.assertEquals((Double)0.0, dtoTiqueteParqueo.getValorAPagar());
		Assert.assertEquals(LocalDateTime.of(2020, Month.SEPTEMBER, 5,0,0,0), dtoTiqueteParqueo.getFechaIngreso());
		Assert.assertEquals(LocalDateTime.of(2020, Month.SEPTEMBER, 5,0,0,0), dtoTiqueteParqueo.getFechaSalida());
	}
}
