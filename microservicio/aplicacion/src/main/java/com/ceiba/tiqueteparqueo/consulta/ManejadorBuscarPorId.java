package com.ceiba.tiqueteparqueo.consulta;

import org.springframework.stereotype.Component;

import com.ceiba.tiqueteparqueo.modelo.dto.DtoTiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.dao.DaoTiqueteParqueo;

@Component
public class ManejadorBuscarPorId {

	private final DaoTiqueteParqueo daoTiqueteParqueo;

	public ManejadorBuscarPorId(DaoTiqueteParqueo daoTiqueteParqueo) {
		this.daoTiqueteParqueo = daoTiqueteParqueo;
	}

	public DtoTiqueteParqueo ejecutar(Long id) {
		return this.daoTiqueteParqueo.buscarTiquetePorId(id);
	}
}
