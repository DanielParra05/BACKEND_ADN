package com.ceiba.tiqueteparqueo.servicio;

import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.testdatabuilder.TiqueteParqueoTestDataBuilder;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class ServicioActualizarTiqueteParqueoTest {
	@Test
	public void actualizarTiqueteParqueo() {
		// arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder().conId(1L).build();
		RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
		 Mockito.when(repositorioTiqueteParqueo.existePorPlaca(Mockito.anyString())).thenReturn(true);
		ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo = new ServicioActualizarTiqueteParqueo(
				repositorioTiqueteParqueo);		
		// act 
		 servicioActualizarTiqueteParqueo.ejecutar(tiqueteParqueo);
		// assert
		Assert.assertTrue(repositorioTiqueteParqueo.existePorPlaca("KDM587"));
	}
}
