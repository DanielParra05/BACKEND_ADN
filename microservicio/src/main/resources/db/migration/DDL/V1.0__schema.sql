create table tiqueteparqueo (
 id int(11) not null auto_increment,
 placa_vehiculo varchar(100) not null,
 tipo_vehiculo varchar(45) not null,
 fecha_ingreso datetime not null,
 fecha_salida datetime,
 valor_pagar double, 
 primary key (id)
);