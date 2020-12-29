package com.ceiba.tiqueteparqueo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tarifario.puerto.dao.DaoTarifario;
import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.testdatabuilder.TiqueteParqueoTestDataBuilder;
import com.ceiba.BasePrueba;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ServicioActualizarTiqueteParqueoTest {
	@Test
	public void actualizarTiqueteSinExistenciaTest() {
		// arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder().conId(1L).build();
		RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		DaoTarifario daoTarifario = Mockito.mock(DaoTarifario.class);

		Mockito.when(repositorioTiqueteParqueo.existePorId(Mockito.anyLong())).thenReturn(false);
		ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo = new ServicioActualizarTiqueteParqueo(
				repositorioTiqueteParqueo, validadorFechaFestivo, daoTarifario);
		// act - assert
		BasePrueba.assertThrows(() -> servicioActualizarTiqueteParqueo.ejecutar(tiqueteParqueo),
				ExcepcionDuplicidad.class, "El tiquete a actualizar no existe en el sistema");
	}

	@Test
	public void actualizarTiqueteConExistenciaTest() {
		// arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder().conId(1L).build();
		RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		DaoTarifario daoTarifario = Mockito.mock(DaoTarifario.class);

		Mockito.when(repositorioTiqueteParqueo.existePorId(Mockito.anyLong())).thenReturn(true);
		ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo = new ServicioActualizarTiqueteParqueo(
				repositorioTiqueteParqueo, validadorFechaFestivo, daoTarifario);
		// act
		servicioActualizarTiqueteParqueo.ejecutar(tiqueteParqueo);
		// assert
		Assert.assertTrue(repositorioTiqueteParqueo.existePorId(1L));
	}

}