package com.ceiba.tiqueteparqueo.controlador;

import java.util.List;

import com.ceiba.tiqueteparqueo.consulta.ManejadorBuscarPorId;
import com.ceiba.tiqueteparqueo.consulta.ManejadorListarTiqueteParqueos;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.tiqueteparqueo.modelo.dto.DtoTiqueteParqueo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tiquetes-parqueo")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags={"Controlador consulta TiqueteParqueo"})
public class ConsultaControladorTiqueteParqueo {

    private final ManejadorListarTiqueteParqueos manejadorListarTiqueteParqueos;
    private final ManejadorBuscarPorId manejadorBuscarPorId;

    public ConsultaControladorTiqueteParqueo(ManejadorListarTiqueteParqueos manejadorListarTiqueteParqueos, ManejadorBuscarPorId manejadorBuscarPorId) {
        this.manejadorListarTiqueteParqueos = manejadorListarTiqueteParqueos;
        this.manejadorBuscarPorId = manejadorBuscarPorId;
    }

    @GetMapping
    @ApiOperation("Listar TiqueteParqueos")
    public List<DtoTiqueteParqueo> listar() {
        return this.manejadorListarTiqueteParqueos.ejecutar();
    }
    
    @GetMapping(value="/{id}")
	@ApiOperation("Buscar TiqueteParqueo por id")
	public DtoTiqueteParqueo buscarTiquetePorId(@PathVariable Long id) {
    	 return this.manejadorBuscarPorId.ejecutar(id);
	}

}
