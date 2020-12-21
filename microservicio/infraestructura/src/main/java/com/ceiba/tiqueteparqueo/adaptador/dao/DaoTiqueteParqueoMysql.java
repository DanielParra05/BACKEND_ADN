package com.ceiba.tiqueteparqueo.adaptador.dao;

import java.util.List;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tiqueteparqueo.puerto.dao.DaoTiqueteParqueo;

import org.springframework.stereotype.Component;

import com.ceiba.tiqueteparqueo.modelo.dto.DtoTiqueteParqueo;

@Component
public class DaoTiqueteParqueoMysql implements DaoTiqueteParqueo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="tiqueteparqueo", value="listar")
    private static String sqlListar;

    public DaoTiqueteParqueoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoTiqueteParqueo> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoTiqueteParqueo());
    }
}
