package com.ceiba.tarifario.controlador;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.tarifario.consulta.ManejadorListarTarifario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tarifario")
@CrossOrigin(origins = "http://localhost:4200/")
@Api(tags = { "Controlador Tarifario" })
public class ConsultaControladorTarifario {

	private final ManejadorListarTarifario manejadorListarTarifario;

	public ConsultaControladorTarifario(ManejadorListarTarifario manejadorListarTarifario) {
		this.manejadorListarTarifario = manejadorListarTarifario;
	}

	@GetMapping
	@ApiOperation("Listar TiqueteParqueos")
	public Map<String, Double> listarTarifas() {
		return manejadorListarTarifario.ejecutar();
	}

}
