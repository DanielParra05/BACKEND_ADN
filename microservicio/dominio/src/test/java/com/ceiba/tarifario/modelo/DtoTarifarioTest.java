package com.ceiba.tarifario.modelo;

import org.junit.Assert;
import org.junit.Test;

import com.ceiba.tarifario.modelo.dto.DtoTarifa;

public class DtoTarifarioTest {
	
	@Test
	public void validarCreacionDtoTarifario() {
		// Arrange - act
		DtoTarifa dtoTarifario = new DtoTarifa(1L, "Test", 3500.0);
		// Assert
		Assert.assertNotNull(dtoTarifario);
		Assert.assertEquals((Long) 1L, dtoTarifario.getId());
		Assert.assertEquals("Test", dtoTarifario.getLlave());
		Assert.assertEquals((Double) 3500.0, dtoTarifario.getValor());
	}

}
