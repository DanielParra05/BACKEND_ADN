package com.ceiba.tiqueteparqueo.servicio;

import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.tiqueteparqueo.servicio.testdatabuilder.TiqueteParqueoTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioCrearTiqueteParqueoTest {

    @Test
    public void validarClaveLongitudMenor4Test() {
        // arrange
        TiqueteParqueoTestDataBuilder TiqueteParqueoTestDataBuilder = new TiqueteParqueoTestDataBuilder().conPlacaVehiculo("124");
        // act - assert
        BasePrueba.assertThrows(() -> TiqueteParqueoTestDataBuilder.build(), ExcepcionLongitudValor.class, "La clave debe tener una longitud mayor o igual a 4");
    }

    @Test
    public void validarTiqueteParqueoExistenciaPreviaTest() {
        // arrange
        TiqueteParqueo TiqueteParqueo = new TiqueteParqueoTestDataBuilder().build();
        RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
        Mockito.when(repositorioTiqueteParqueo.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearTiqueteParqueo servicioCrearTiqueteParqueo = new ServicioCrearTiqueteParqueo(repositorioTiqueteParqueo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearTiqueteParqueo.ejecutar(TiqueteParqueo), ExcepcionDuplicidad.class,"El TiqueteParqueo ya existe en el sistema");
    }
}
