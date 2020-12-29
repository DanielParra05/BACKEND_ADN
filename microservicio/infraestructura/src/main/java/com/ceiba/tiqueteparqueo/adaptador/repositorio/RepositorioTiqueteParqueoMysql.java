package com.ceiba.tiqueteparqueo.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioTiqueteParqueoMysql implements RepositorioTiqueteParqueo {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "tiqueteparqueo", value = "crear")
	private static String sqlCrear;

	@SqlStatement(namespace = "tiqueteparqueo", value = "actualizar")
	private static String sqlActualizar;

	@SqlStatement(namespace = "tiqueteparqueo", value = "eliminar")
	private static String sqlEliminar;

	@SqlStatement(namespace = "tiqueteparqueo", value = "existeSinFechaSalida")
	private static String sqlExisteSinFechaSalida;

	@SqlStatement(namespace = "tiqueteparqueo", value = "existePorId")
	private static String sqlExistePorId;

	public RepositorioTiqueteParqueoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(TiqueteParqueo tiqueteParqueo) {
		return this.customNamedParameterJdbcTemplate.crear(tiqueteParqueo, sqlCrear);
	}

	@Override
	public void eliminar(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);

		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
	}

	@Override
	public void actualizar(TiqueteParqueo tiqueteParqueo) {
		this.customNamedParameterJdbcTemplate.actualizar(tiqueteParqueo, sqlActualizar);
	}

	@Override
	public boolean existePorPlacaAndSinFechaSalida(String placaVehiculo) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("placaVehiculo", placaVehiculo);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
				.queryForObject(sqlExisteSinFechaSalida, paramSource, Boolean.class);
	}

	@Override
	public boolean existePorId(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,
				paramSource, Boolean.class);
	}
}
