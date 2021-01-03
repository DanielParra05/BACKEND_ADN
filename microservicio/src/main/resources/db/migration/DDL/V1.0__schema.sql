create table tiqueteparqueo (
 id int(11) not null auto_increment,
 placa_vehiculo varchar(100) not null,
 tipo_vehiculo varchar(45) not null,
 fecha_ingreso datetime not null,
 fecha_salida datetime,
 valor_pagar double, 
 primary key (id)
);

create table tarifario (
 id int(11) not null auto_increment,
 llave varchar(100) not null,
 valor double not null,
 primary key (id)
);

create table user (
 id int(11) not null auto_increment,
 username varchar(100) not null unique,
 password varchar(1000) not null,
 primary key (id)
);