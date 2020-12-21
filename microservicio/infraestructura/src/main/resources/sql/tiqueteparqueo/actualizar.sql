update tiqueteparqueo
set placa_vehiculo = :placa_vehiculo,
	tipo_vehiculo = :tipo_vehiculo,
	fecha_ingreso = :fecha_ingreso,
	fecha_salida = :fecha_salida,
	valor_pagar = :valor_pagar
where id = :id