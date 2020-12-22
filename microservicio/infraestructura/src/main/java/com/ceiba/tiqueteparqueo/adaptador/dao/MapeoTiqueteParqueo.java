package com.ceiba.tiqueteparqueo.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.tiqueteparqueo.modelo.dto.DtoTiqueteParqueo;
import org.springframework.jdbc.core.RowMapper;

public class MapeoTiqueteParqueo implements RowMapper<DtoTiqueteParqueo>, MapperResult {

    @Override
    public DtoTiqueteParqueo mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String placaVehiculo = resultSet.getString("placa_vehiculo");
        String tipoVehiculo = resultSet.getString("tipo_vehiculo");
        LocalDateTime fechaIngreso = extraerLocalDateTime(resultSet, "fecha_ingreso");
        LocalDateTime fechaSalida = extraerLocalDateTime(resultSet, "fecha_salida");
        Double valorAPagar = resultSet.getDouble("valor_pagar");

        return new DtoTiqueteParqueo(id,placaVehiculo,tipoVehiculo,fechaIngreso,fechaSalida,valorAPagar);
    }

}
