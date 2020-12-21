package com.ceiba.tiqueteparqueo.puerto.dao;

import com.ceiba.tiqueteparqueo.modelo.dto.DtoTiqueteParqueo;

import java.util.List;

public interface DaoTiqueteParqueo {

    /**
     * Permite listar TiqueteParqueos
     * @return los TiqueteParqueos
     */
    List<DtoTiqueteParqueo> listar();
}
