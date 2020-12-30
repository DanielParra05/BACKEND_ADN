package com.ceiba.tarifario.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tarifario.modelo.entidad.Tarifa;
import com.ceiba.tarifario.puerto.repositorio.RepositorioTarifario;

@Repository
public class RepositorioTarifarioMySql implements RepositorioTarifario {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "tarifario", value = "crear")
	private static String sqlCrear;

	@SqlStatement(namespace = "tarifario", value = "actualizar")
	private static String sqlActualizar;

	@SqlStatement(namespace = "tarifario", value = "eliminar")
	private static String sqlEliminar;
	
	@SqlStatement(namespace = "tarifario", value = "existePorLlave")
	private static String sqlExistePorLlave;

	public RepositorioTarifarioMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(Tarifa tarifa) {
		return this.customNamedParameterJdbcTemplate.crear(tarifa, sqlCrear);
	}

	@Override
	public void actualizar(Tarifa tarifa) {
		this.customNamedParameterJdbcTemplate.actualizar(tarifa, sqlActualizar);
	}

	@Override
	public void eliminar(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);

		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
	}
	
	@Override
	public boolean existePorLlave(String llave) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("llave", llave);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorLlave,
				paramSource, Boolean.class);
	}

}
