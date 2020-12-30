package com.ceiba.tarifario.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.tarifario.modelo.dto.DtoTarifa;

public class MapeoTarifario implements RowMapper<DtoTarifa>, MapperResult {

	@Override
	public DtoTarifa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String llave = rs.getString("llave");
		Double valor = rs.getDouble("valor");

		return new DtoTarifa(id, llave, valor);
	}

}
