package com.ceiba.infraestructura.auth.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class DaoUserMySql {
	
	@SqlStatement(namespace = "user", value = "listar")
	private static String sqlListar;
	
	@Autowired
	private CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;	
	
	public HashMap<String, String> listar() {
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar,
				new ResultSetExtractor<HashMap<String, String>>() {
					@Override
					public HashMap<String, String> extractData(ResultSet rs) throws SQLException {
						HashMap<String, String> mapRet = new HashMap<>();
						while (rs.next()) {
							mapRet.put(rs.getString("username"), rs.getString("password"));
						}
						return mapRet;
					}
				});
	}

}
