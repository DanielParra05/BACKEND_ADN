package com.ceiba.tiqueteparqueo.consulta;

import java.util.List;

import com.ceiba.tiqueteparqueo.puerto.dao.DaoTiqueteParqueo;
import org.springframework.stereotype.Component;

import com.ceiba.tiqueteparqueo.modelo.dto.DtoTiqueteParqueo;

@Component
public class ManejadorListarTiqueteParqueos {

	private final DaoTiqueteParqueo daoTiqueteParqueo;

	public ManejadorListarTiqueteParqueos(DaoTiqueteParqueo daoTiqueteParqueo) {
		this.daoTiqueteParqueo = daoTiqueteParqueo;
	}

	public List<DtoTiqueteParqueo> ejecutar() {
		return this.daoTiqueteParqueo.listar();
	}

}
