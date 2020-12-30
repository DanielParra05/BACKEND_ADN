package com.ceiba.tarifario.servicio;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.tarifario.puerto.repositorio.RepositorioTarifario;

public class ServicioEliminarTarifarioTest {

	@Test
	public void validarEliminarTarifa() {
		//Arrange
		RepositorioTarifario repositorio = Mockito.mock(RepositorioTarifario.class);		
		Mockito.when(repositorio.existePorLlave(Mockito.anyString())).thenReturn(false);
		ServicioEliminarTarifa servicioActualizar = new ServicioEliminarTarifa(repositorio);		
		//Act
		servicioActualizar.ejecutar(1L);		
		//Assert
		Assert.assertFalse(repositorio.existePorLlave("Test"));
	}
	
}
