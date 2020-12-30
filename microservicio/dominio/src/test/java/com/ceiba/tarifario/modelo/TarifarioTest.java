package com.ceiba.tarifario.modelo;

import org.junit.Assert;
import org.junit.Test;

import com.ceiba.tarifario.modelo.entidad.Tarifa;
import com.ceiba.tarifario.servicio.testdatabuilder.TarifarioTestDataBuilder;

public class TarifarioTest {

	@Test
	public void validarConstructorTarifa() {
		// arrange
		Tarifa tarifa = new TarifarioTestDataBuilder().conId(1L).conValor(3500.0).build();

		// act - assert
		Assert.assertEquals((Long) 1L, tarifa.getId());
		Assert.assertEquals((Double) 3500.0, tarifa.getValor());
	}

}
