package com.ceiba.infraestructura.servicio.externo.superfinancieratrm;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/trm")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags={"Controlador consulta de Servicio Soap de TRM"})
public class ConsultaControladorTrm {
	
	@GetMapping
	public float getTrm() {
		return ClienteSuperfinancieraWebServiceTrm.getTrm();
	}
	
	
}
