package com.ceiba.tiqueteparqueo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.tarifario.puerto.dao.DaoTarifario;
import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.testdatabuilder.TiqueteParqueoTestDataBuilder;
import com.ceiba.BasePrueba;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

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
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
		DaoTarifario daoTarifario = Mockito.mock(DaoTarifario.class);

		Mockito.when(repositorioTiqueteParqueo.existePorId(Mockito.anyLong())).thenReturn(true);
		ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo = new ServicioActualizarTiqueteParqueo(
				repositorioTiqueteParqueo, validadorFechaFestivo, daoTarifario);
		// act
		servicioActualizarTiqueteParqueo.ejecutar(tiqueteParqueo);
		// assert
		Assert.assertTrue(repositorioTiqueteParqueo.existePorId(1L));
	}

	/**
	 *-------- PRUEBAS DE CIERRE DEL TIQUETE -------------
	 */
	@Test
	public void validarValorAPagarConRecargoNocturno() throws IOException {
		// Arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder()
				.conFechaIngreso(LocalDateTime.of(2020, Month.JULY, 21, 18, 20))
				.conFechaSalida(LocalDateTime.of(2020, Month.JULY, 21, 23, 30)).build();
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
		DaoTarifario daoTarifario = Mockito.mock(DaoTarifario.class);

		Mockito.when(validadorFechaFestivo.esFestivo(Mockito.anyObject())).thenReturn(false);
		Mockito.when(repositorioTiqueteParqueo.existePorId(Mockito.anyLong())).thenReturn(true);
		doAnswer(invocation -> this.generarTarifario()).when(daoTarifario).listar();

		ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo = new ServicioActualizarTiqueteParqueo(
				repositorioTiqueteParqueo, validadorFechaFestivo, daoTarifario);

		// Act
		servicioActualizarTiqueteParqueo.ejecutar(tiqueteParqueo);
		// Assert
		Assert.assertEquals((Double) 19000.0, tiqueteParqueo.getValorAPagar());
	}

	@Test
	public void validarValorAPagarConFechaIngresoFestivo() throws IOException {
		// Arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder()
				.conFechaIngreso(LocalDateTime.of(2020, Month.JULY, 20, 12, 0))
				.conFechaSalida(LocalDateTime.of(2020, Month.JULY, 20, 16, 20)).build();
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
		DaoTarifario daoTarifario = Mockito.mock(DaoTarifario.class);

		Mockito.when(validadorFechaFestivo.esFestivo(Mockito.anyObject())).thenReturn(true);
		Mockito.when(repositorioTiqueteParqueo.existePorId(Mockito.anyLong())).thenReturn(true);
		doAnswer(invocation -> this.generarTarifario()).when(daoTarifario).listar();

		ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo = new ServicioActualizarTiqueteParqueo(
				repositorioTiqueteParqueo, validadorFechaFestivo, daoTarifario);

		// Act
		servicioActualizarTiqueteParqueo.ejecutar(tiqueteParqueo);
		// Assert
		Assert.assertEquals((Double) 6250.0, tiqueteParqueo.getValorAPagar());
	}

	@Test
	public void validarValorAPagarConFechaIngresoFinDeSemana() throws IOException {
		// Arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder()
				.conFechaIngreso(LocalDateTime.of(2020, Month.JULY, 19, 12, 0))
				.conFechaSalida(LocalDateTime.of(2020, Month.JULY, 19, 15, 0)).build();
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
		DaoTarifario daoTarifario = Mockito.mock(DaoTarifario.class);

		Mockito.when(validadorFechaFestivo.esFestivo(Mockito.anyObject())).thenReturn(false);
		Mockito.when(repositorioTiqueteParqueo.existePorId(Mockito.anyLong())).thenReturn(true);
		doAnswer(invocation -> this.generarTarifario()).when(daoTarifario).listar();

		ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo = new ServicioActualizarTiqueteParqueo(
				repositorioTiqueteParqueo, validadorFechaFestivo, daoTarifario);

		// Act
		servicioActualizarTiqueteParqueo.ejecutar(tiqueteParqueo);
		// Assert
		Assert.assertEquals((Double) 9000.0, tiqueteParqueo.getValorAPagar());
	}

	@Test
	public void validarValorAPagarEnLimpio() throws IOException {
		// Arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder()
				.conFechaIngreso(LocalDateTime.of(2020, Month.JULY, 24, 12, 0))
				.conFechaSalida(LocalDateTime.of(2020, Month.JULY, 24, 15, 0)).build();
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
		DaoTarifario daoTarifario = Mockito.mock(DaoTarifario.class);

		Mockito.when(validadorFechaFestivo.esFestivo(Mockito.anyObject())).thenReturn(false);
		Mockito.when(repositorioTiqueteParqueo.existePorId(Mockito.anyLong())).thenReturn(true);
		doAnswer(invocation -> this.generarTarifario()).when(daoTarifario).listar();

		ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo = new ServicioActualizarTiqueteParqueo(
				repositorioTiqueteParqueo, validadorFechaFestivo, daoTarifario);

		// Act
		servicioActualizarTiqueteParqueo.ejecutar(tiqueteParqueo);
		// Assert
		Assert.assertEquals((Double) 7500.0, tiqueteParqueo.getValorAPagar());
	}
	
	@Test
	public void falloAPIFechaFestivo() throws IOException {
		// Arrange
		TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder()
				.conFechaIngreso(LocalDateTime.of(2020, Month.JULY, 24, 12, 0))
				.conFechaSalida(LocalDateTime.of(2020, Month.JULY, 24, 15, 0)).build();
		ApiValidadorFechaFestivo validadorFechaFestivo = Mockito.mock(ApiValidadorFechaFestivo.class);
		RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
		DaoTarifario daoTarifario = Mockito.mock(DaoTarifario.class);
		
		doThrow(IOException.class).when(validadorFechaFestivo).esFestivo((Mockito.any(LocalDateTime.class)));
		Mockito.when(repositorioTiqueteParqueo.existePorId(Mockito.anyLong())).thenReturn(true);
		doAnswer(invocation -> this.generarTarifario()).when(daoTarifario).listar();

		ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo = new ServicioActualizarTiqueteParqueo(
				repositorioTiqueteParqueo, validadorFechaFestivo, daoTarifario);

		// act - assert
		BasePrueba.assertThrows(() -> servicioActualizarTiqueteParqueo.ejecutar(tiqueteParqueo),
				ExcepcionSinDatos.class, "La API de validacion de festivos ha fallado: ");
	}

	private Map<String, Double> generarTarifario() {
		return new HashMap<String, Double>() {
			{
				put("Recargo_Nocturno", 4000.0);
				put("CARRO", 2500.0);
			}
		};
	}
}