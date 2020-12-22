update tiqueteparqueo
set placa_vehiculo = :placaVehiculo,
	tipo_vehiculo = :tipoVehiculo,
	fecha_ingreso = :fechaIngreso,
	fecha_salida = :fechaSalida,
	valor_pagar = :valorAPagar
where id = :id