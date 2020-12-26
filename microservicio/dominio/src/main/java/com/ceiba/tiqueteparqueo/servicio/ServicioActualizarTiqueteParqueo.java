package com.ceiba.tiqueteparqueo.servicio;

import java.io.IOException;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;
import com.ceiba.tiqueteparqueo.puerto.dao.DaoTarifario;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;

public class ServicioActualizarTiqueteParqueo {

	private final RepositorioTiqueteParqueo repositorioTiqueteParqueo;
	private final ApiValidadorFechaFestivo validadorFechaFestivo;
	private final DaoTarifario daoTarifario;
	private static final String EL_SERVICIO_DE_VALIDACION_FESTIVOS_FALLO = "La API de validacion de festivos ha fallado";
	private static final String EL_TIQUETE_NO_EXISTE_EN_EL_SISTEMA = "El tiquete a actualizar no existe en el sistema";

	public ServicioActualizarTiqueteParqueo(RepositorioTiqueteParqueo repositorioTiqueteParqueo,
			ApiValidadorFechaFestivo validadorFechaFestivo, DaoTarifario daoTarifario) {
		this.repositorioTiqueteParqueo = repositorioTiqueteParqueo;
		this.validadorFechaFestivo = validadorFechaFestivo;
		this.daoTarifario = daoTarifario;
	}

	public void ejecutar(TiqueteParqueo tiqueteParqueo) {
		validarExistenciaPrevia(tiqueteParqueo.getId());
		try {
			tiqueteParqueo.asignarValorPagar(validadorFechaFestivo, daoTarifario.listar());
			this.repositorioTiqueteParqueo.actualizar(tiqueteParqueo);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExcepcionSinDatos(EL_SERVICIO_DE_VALIDACION_FESTIVOS_FALLO);
		}
	}

	private void validarExistenciaPrevia(Long id) {
		boolean existe = repositorioTiqueteParqueo.existePorId(id);
		if (!existe) {
			throw new ExcepcionDuplicidad(EL_TIQUETE_NO_EXISTE_EN_EL_SISTEMA);
		}
	}

}
