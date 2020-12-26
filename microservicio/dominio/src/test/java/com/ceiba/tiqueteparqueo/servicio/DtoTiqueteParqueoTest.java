package com.ceiba.tiqueteparqueo.servicio;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.ceiba.tiqueteparqueo.modelo.dto.DtoTiqueteParqueo;

public class DtoTiqueteParqueoTest {

	@Test
	public void validarCreacionDtoTiqueteParqueo() {
		// arrange - act
		DtoTiqueteParqueo dtoTiqueteParqueo = new DtoTiqueteParqueo(1L, "GKK550", "Carro", LocalDateTime.now(),
				LocalDateTime.now(), 0.0);
		// assert
		Assert.assertTrue(dtoTiqueteParqueo != null);
	}
}
