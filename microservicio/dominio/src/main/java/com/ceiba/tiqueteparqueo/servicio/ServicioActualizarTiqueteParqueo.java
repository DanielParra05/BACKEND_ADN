package com.ceiba.tiqueteparqueo.servicio;

import java.io.IOException;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.tiqueteparqueo.modelo.dto.DtoTiqueteParqueo;
import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;
import com.ceiba.tiqueteparqueo.puerto.dao.DaoTarifario;
import com.ceiba.tiqueteparqueo.puerto.dao.DaoTiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;

public class ServicioActualizarTiqueteParqueo {

	private final RepositorioTiqueteParqueo repositorioTiqueteParqueo;
	private final DaoTiqueteParqueo daoTiqueteParqueo;
	private final ApiValidadorFechaFestivo validadorFechaFestivo;
	private final DaoTarifario daoTarifario;
	private static final String EL_SERVICIO_DE_VALIDACION_FESTIVOS_FALLO = "La API de validacion de festivos ha fallado: ";
	private static final String EL_TIQUETE_NO_EXISTE_EN_EL_SISTEMA = "El tiquete a actualizar no existe en el sistema";

	public ServicioActualizarTiqueteParqueo(RepositorioTiqueteParqueo repositorioTiqueteParqueo,
			ApiValidadorFechaFestivo validadorFechaFestivo, DaoTarifario daoTarifario, DaoTiqueteParqueo daoTiqueteParqueo) {
		this.repositorioTiqueteParqueo = repositorioTiqueteParqueo;
		this.validadorFechaFestivo = validadorFechaFestivo;
		this.daoTarifario = daoTarifario;
		this.daoTiqueteParqueo = daoTiqueteParqueo;
	}

	public void ejecutar(TiqueteParqueo tiqueteParqueo) {
		validarExistenciaPrevia(tiqueteParqueo.getId());
		try {
			tiqueteParqueo.asignarValorPagar(validadorFechaFestivo, daoTarifario.listar());
			this.repositorioTiqueteParqueo.actualizar(tiqueteParqueo);
		} catch (IOException e) {
			throw new ExcepcionSinDatos(EL_SERVICIO_DE_VALIDACION_FESTIVOS_FALLO + e.getMessage());
		}
	}

	private void validarExistenciaPrevia(Long id) {
		DtoTiqueteParqueo tiquete = daoTiqueteParqueo.buscarTiquetePorId(id);
		if (tiquete == null) {
			throw new ExcepcionDuplicidad(EL_TIQUETE_NO_EXISTE_EN_EL_SISTEMA);
		}
	}

}
