package com.ceiba.tarifario.controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.tarifario.comando.ComandoTarifario;
import com.ceiba.tarifario.comando.manejador.ManejadorActualizarTarifa;
import com.ceiba.tarifario.comando.manejador.ManejadorCrearTarifa;
import com.ceiba.tarifario.comando.manejador.ManejadorEliminarTarifa;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tarifario")
@CrossOrigin(origins = "http://localhost:4200/")
@Api(tags = { "Controlador Tarifario" })
public class ComandoControladorTarifario {

	private final ManejadorCrearTarifa manejadorCrear;
	private final ManejadorActualizarTarifa manejadorActualizar;
	private final ManejadorEliminarTarifa manejadorEliminar;

	public ComandoControladorTarifario(ManejadorCrearTarifa manejadorCrear,
			ManejadorActualizarTarifa manejadorActualizar, ManejadorEliminarTarifa manejadorEliminar) {
		this.manejadorCrear = manejadorCrear;
		this.manejadorActualizar = manejadorActualizar;
		this.manejadorEliminar = manejadorEliminar;
	}

	@PostMapping
	@ApiOperation("Crear tarifa")
	public ComandoRespuesta<Long> crearTarifa(@RequestBody ComandoTarifario comandoTarifario) {
		return manejadorCrear.ejecutar(comandoTarifario);
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation("Actualizar tarifa")
	public void actualizarTarifa(@PathVariable Long id, @RequestBody ComandoTarifario comandoTarifario) {
		comandoTarifario.setId(id);
		this.manejadorActualizar.ejecutar(comandoTarifario);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation("Eliminar tarifa")
	public void eliminarTarifa(@PathVariable Long id) {
		this.manejadorEliminar.ejecutar(id);
	}
	
	

}
