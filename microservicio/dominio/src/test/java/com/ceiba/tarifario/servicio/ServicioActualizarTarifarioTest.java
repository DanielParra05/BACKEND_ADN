package com.ceiba.tarifario.servicio;


import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Assert;

import com.ceiba.tarifario.modelo.entidad.Tarifa;
import com.ceiba.tarifario.puerto.repositorio.RepositorioTarifario;
import com.ceiba.tarifario.servicio.testdatabuilder.TarifarioTestDataBuilder;
public class ServicioActualizarTarifarioTest {

	@Test
	public void actualizarTarifario() {
		//Arrange
		Tarifa tarifa = new TarifarioTestDataBuilder().conLlave("Test1").build();		
		RepositorioTarifario repositorio = Mockito.mock(RepositorioTarifario.class);		
		Mockito.when(repositorio.existePorLlave(Mockito.anyString())).thenReturn(true);
		ServicioActualizarTarifa servicioActualizar = new ServicioActualizarTarifa(repositorio);
		
		// act
		servicioActualizar.ejecutar(tarifa);
		
		//assert
		Assert.assertTrue(repositorio.existePorLlave("Test1"));
	}
	
}
