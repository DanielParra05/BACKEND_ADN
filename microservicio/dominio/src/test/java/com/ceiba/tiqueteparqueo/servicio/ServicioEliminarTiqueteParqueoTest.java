package com.ceiba.tiqueteparqueo.servicio;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;

public class ServicioEliminarTiqueteParqueoTest {
	@Test
	public void validarEliminarTiqueteParqueo() {
		// arrange
		RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
		Mockito.when(repositorioTiqueteParqueo.existePorId(Mockito.anyLong())).thenReturn(false);
		ServicioEliminarTiqueteParqueo servicioEliminarTiqueteParqueo = new ServicioEliminarTiqueteParqueo(
				repositorioTiqueteParqueo);
		// act
		servicioEliminarTiqueteParqueo.ejecutar(1L);
		// assert
		Assert.assertFalse(repositorioTiqueteParqueo.existePorId(1L));
	}
}
