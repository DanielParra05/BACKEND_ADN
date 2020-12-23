package com.ceiba.tiqueteparqueo.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.tiqueteparqueo.comando.ComandoTiqueteParqueo;
import com.ceiba.tiqueteparqueo.comando.manejador.ManejadorActualizarTiqueteParqueo;
import com.ceiba.tiqueteparqueo.comando.manejador.ManejadorCrearTiqueteParqueo;
import com.ceiba.tiqueteparqueo.comando.manejador.ManejadorEliminarTiqueteParqueo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tiquete-parqueo")
@Api(tags = { "Controlador comando TiqueteParqueo"})
public class ComandoControladorTiqueteParqueo {

    private final ManejadorCrearTiqueteParqueo manejadorCrearTiqueteParqueo;
	private final ManejadorEliminarTiqueteParqueo manejadorEliminarTiqueteParqueo;
	private final ManejadorActualizarTiqueteParqueo manejadorActualizarTiqueteParqueo;

    @Autowired
    public ComandoControladorTiqueteParqueo(ManejadorCrearTiqueteParqueo manejadorCrearTiqueteParqueo,
									 ManejadorEliminarTiqueteParqueo manejadorEliminarTiqueteParqueo,
									 ManejadorActualizarTiqueteParqueo manejadorActualizarTiqueteParqueo) {
        this.manejadorCrearTiqueteParqueo = manejadorCrearTiqueteParqueo;
		this.manejadorEliminarTiqueteParqueo = manejadorEliminarTiqueteParqueo;
		this.manejadorActualizarTiqueteParqueo = manejadorActualizarTiqueteParqueo;
    }

    @PostMapping
    @ApiOperation("Crear TiqueteParqueo")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoTiqueteParqueo comandoTiqueteParqueo) {
        return manejadorCrearTiqueteParqueo.ejecutar(comandoTiqueteParqueo);
    }

    @DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar TiqueteParqueo")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarTiqueteParqueo.ejecutar(id);
	}

	@PutMapping(value="/{id}")
	@ApiOperation("Actualizar TiqueteParqueo")
	public void actualizar(@RequestBody ComandoTiqueteParqueo comandoTiqueteParqueo,@PathVariable Long id) {
		comandoTiqueteParqueo.setId(id);
		manejadorActualizarTiqueteParqueo.ejecutar(comandoTiqueteParqueo);
	}
}
