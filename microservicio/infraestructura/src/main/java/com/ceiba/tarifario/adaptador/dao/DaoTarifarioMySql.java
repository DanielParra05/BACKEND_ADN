package com.ceiba.tarifario.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tarifario.modelo.dto.DtoTarifa;
import com.ceiba.tarifario.puerto.dao.DaoTarifario;
import com.ceiba.tiqueteparqueo.adaptador.dao.MapeoTiqueteParqueo;

@Component
public class DaoTarifarioMySql implements DaoTarifario {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "tarifario", value = "listar")
	private static String sqlListar;
	
	@SqlStatement(namespace = "tarifario", value = "listarObjeto")
	private static String sqlListarObjeto;
	
	@SqlStatement(namespace = "tarifario", value = "buscarPorId")
	private static String sqlBuscarPorId;

	public DaoTarifarioMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public HashMap<String, Double> listar() {
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar,
				new ResultSetExtractor<HashMap<String, Double>>() {
					@Override
					public HashMap<String, Double> extractData(ResultSet rs) throws SQLException {
						HashMap<String, Double> mapRet = new HashMap<>();
						while (rs.next()) {
							mapRet.put(rs.getString("llave"), rs.getDouble("valor"));
						}
						return mapRet;
					}
				});
	}

	@Override
	public List<DtoTarifa> listarComoObjetos() {
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarObjeto,
				new MapeoTarifario());
	}

	@Override
	public DtoTarifa buscarPorId(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarPorId,
				paramSource, new MapeoTarifario());
	}
}
