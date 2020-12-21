package com.ceiba.tiqueteparqueo.servicio;

import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tiqueteparqueo.servicio.testdatabuilder.TiqueteParqueoTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioActualizarTiqueteParqueoTest {

    @Test
    public void validarTiqueteParqueoExistenciaPreviaTest() {
        // arrange
        TiqueteParqueo TiqueteParqueo = new TiqueteParqueoTestDataBuilder().conId(1L).build();
        RepositorioTiqueteParqueo repositorioTiqueteParqueo = Mockito.mock(RepositorioTiqueteParqueo.class);
        Mockito.when(repositorioTiqueteParqueo.existeSinFechaSalida(Mockito.anyString())).thenReturn(true);
        ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo = new ServicioActualizarTiqueteParqueo(repositorioTiqueteParqueo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarTiqueteParqueo.ejecutar(TiqueteParqueo), ExcepcionDuplicidad.class,"El TiqueteParqueo ya existe en el sistema");
    }
}
