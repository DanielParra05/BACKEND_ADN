package com.ceiba.tiqueteparqueo.servicio;

import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tiqueteparqueo.servicio.testdatabuilder.TiqueteParqueoTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioCrearTiqueteParqueoTest {

	/**
    @Test
    public void validarClaveLongitudMenor4Test() {
        // arrange
        TiqueteParqueoTestDataBuilder TiqueteParqueoTestDataBuilder = new TiqueteParqueoTestDataBuilder().conPlacaVehiculo("124");
        // act - assert
        BasePrueba.assertThrows(() -> TiqueteParqueoTestDataBuilder.build(), ExcepcionLongitudValor.class, "La clave debe tener una longitud mayor o igual a 4");
    }*/

    @Test
    public void validarTiqueteParqueoExistenciaActivaTest() {
        // arrange
        TiqueteParqueo tiqueteParqueo = new TiqueteParqueoTestDataBuilder().build();
        RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
        Mockito.when(repositorioTiqueteParqueo.existeSinFechaSalida(Mockito.anyString())).thenReturn(true);
        ServicioCrearTiqueteParqueo servicioCrearTiqueteParqueo = new ServicioCrearTiqueteParqueo(repositorioTiqueteParqueo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearTiqueteParqueo.ejecutar(tiqueteParqueo), ExcepcionDuplicidad.class,"El vehiculo ya cuenta con un tiquete activo");
    }
}
