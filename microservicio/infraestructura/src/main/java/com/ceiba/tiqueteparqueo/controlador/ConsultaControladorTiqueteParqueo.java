package com.ceiba.tiqueteparqueo.controlador;

import java.util.List;

import com.ceiba.tiqueteparqueo.consulta.ManejadorListarTiqueteParqueos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.tiqueteparqueo.modelo.dto.DtoTiqueteParqueo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/TiqueteParqueos")
@Api(tags={"Controlador consulta TiqueteParqueo"})
public class ConsultaControladorTiqueteParqueo {

    private final ManejadorListarTiqueteParqueos manejadorListarTiqueteParqueos;

    public ConsultaControladorTiqueteParqueo(ManejadorListarTiqueteParqueos manejadorListarTiqueteParqueos) {
        this.manejadorListarTiqueteParqueos = manejadorListarTiqueteParqueos;
    }

    @GetMapping
    @ApiOperation("Listar TiqueteParqueos")
    public List<DtoTiqueteParqueo> listar() {
        return this.manejadorListarTiqueteParqueos.ejecutar();
    }

}
