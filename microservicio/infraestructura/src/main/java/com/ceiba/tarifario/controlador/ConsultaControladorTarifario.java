package com.ceiba.tarifario.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.tarifario.consulta.ManejadorBuscarTarifaPorId;
import com.ceiba.tarifario.consulta.ManejadorListarTarifario;
import com.ceiba.tarifario.modelo.dto.DtoTarifa;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tarifario")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = { "Controlador Tarifario" })
public class ConsultaControladorTarifario {

	private final ManejadorListarTarifario manejadorListarTarifario;
	private final ManejadorBuscarTarifaPorId manejadorBuscarTarifaPorId;

	public ConsultaControladorTarifario(ManejadorListarTarifario manejadorListarTarifario,
			ManejadorBuscarTarifaPorId manejadorBuscarTarifaPorId) {
		this.manejadorListarTarifario = manejadorListarTarifario;
		this.manejadorBuscarTarifaPorId = manejadorBuscarTarifaPorId;
	}

	@GetMapping
	@ApiOperation("Listar TiqueteParqueos")
	public List<DtoTarifa> listarTarifas() {
		return manejadorListarTarifario.ejecutar();
	}

	@GetMapping(value = "/{id}")
	@ApiOperation("Busca una tarifa por su id")
	public DtoTarifa buscartarifaPorId(@PathVariable Long id) {
		return manejadorBuscarTarifaPorId.ejecutar(id);
	}

}
