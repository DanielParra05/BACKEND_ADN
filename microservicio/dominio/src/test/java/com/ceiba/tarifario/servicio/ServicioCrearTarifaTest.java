package com.ceiba.tarifario.servicio;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tarifario.modelo.entidad.Tarifa;
import com.ceiba.tarifario.puerto.repositorio.RepositorioTarifario;
import com.ceiba.tarifario.servicio.testdatabuilder.TarifarioTestDataBuilder;


public class ServicioCrearTarifaTest {
	
	@Test
	public void crearTarifaExistenciaPrevia() {
		//Arrange
		Tarifa tarifa = new TarifarioTestDataBuilder().build();
		RepositorioTarifario repositorio = Mockito.mock(RepositorioTarifario.class);		
		Mockito.when(repositorio.existePorLlave(Mockito.anyString())).thenReturn(true);
		ServicioCrearTarifa servicioCrear = new ServicioCrearTarifa(repositorio);		
		//Act-Assert
		BasePrueba.assertThrows(() -> servicioCrear.ejecutar(tarifa), ExcepcionDuplicidad.class,
				"La tarifa ya existe");
	}
	
	@Test
	public void crearTarifaSinExistenciaPrevia() {
		//Arrange
		Tarifa tarifa = new TarifarioTestDataBuilder().conLlave("Test").build();
		RepositorioTarifario repositorio = Mockito.mock(RepositorioTarifario.class);		
		Mockito.when(repositorio.existePorLlave(Mockito.anyString())).thenReturn(false);
		ServicioCrearTarifa servicioCrear = new ServicioCrearTarifa(repositorio);		
		//Act
		long resultado = servicioCrear.ejecutar(tarifa);
		//Assert
		Assert.assertEquals(0L, resultado);
	}

}
