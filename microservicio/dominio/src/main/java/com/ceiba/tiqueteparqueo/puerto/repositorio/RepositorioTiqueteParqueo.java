package com.ceiba.tiqueteparqueo.puerto.repositorio;

import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;

public interface RepositorioTiqueteParqueo {
    /**
     * Permite crear un tiquete de parqueo
     * @param tiqueteParqueo
     * @return el id generado
     */
    Long crear(TiqueteParqueo tiqueteParqueo);

    /**
     * Permite actualizar un tiquete de parqueo
     * @param tiqueteParqueo
     */
    void actualizar(TiqueteParqueo tiqueteParqueo);

    /**
     * Permite eliminar un tiquete de parqueo
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un tiquete de parqueo con una placa
     * @param nombre
     * @return si existe o no
     */
    boolean existePorPlaca(String placa);
    
    /**
     * Permite validar si existe un tiquete de parqueo por su id
     * @param nombre
     * @return si existe o no
     */
    boolean existePorId(Long id);

    /**
     * Permite validar si existe un TiqueteParqueo con determinada placa  y sin fecha de salida
     * @param placa
     * @return si existe o no
     */
    boolean existePorPlacaAndSinFechaSalida(String placa);

}
