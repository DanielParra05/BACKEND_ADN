package com.ceiba.tiqueteparqueo.servicio;

import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;
import com.ceiba.tiqueteparqueo.puerto.dao.DaoTarifario;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;

public class ServicioActualizarTiqueteParqueo {

	private final RepositorioTiqueteParqueo repositorioTiqueteParqueo;
	private final ApiValidadorFechaFestivo validadorFechaFestivo;
	private final DaoTarifario daoTarifario;

	public ServicioActualizarTiqueteParqueo(RepositorioTiqueteParqueo repositorioTiqueteParqueo,
			ApiValidadorFechaFestivo validadorFechaFestivo, DaoTarifario daoTarifario) {
		this.repositorioTiqueteParqueo = repositorioTiqueteParqueo;
		this.validadorFechaFestivo = validadorFechaFestivo;
		this.daoTarifario = daoTarifario;
	}

	public void ejecutar(TiqueteParqueo tiqueteParqueo) {
		tiqueteParqueo.asignarValorPagar(validadorFechaFestivo, daoTarifario.listar());
		this.repositorioTiqueteParqueo.actualizar(tiqueteParqueo);
	}

}
