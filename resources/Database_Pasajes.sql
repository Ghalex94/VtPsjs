create database db_venta_pasajes;

use db_venta_pasajes;

create table tb_usuario(
usuario 		varchar(20) not null primary key,
pass			varchar(20),
nombre			varchar(50),
tipo 		 	int
);

create table tb_empresa(
idempresa		int primary key auto_increment,
ruc 			varchar(11),
empresa 		varchar(60)
);

create table tb_conductor(
dniconductor	int not null primary key,
licencia		varchar(30),
conductor		varchar(50)
);

create table tb_modelo_vehiculo(
idmodelo		int primary key auto_increment,
modelo 			varchar(50),
casientos		int
);

create table tb_vehiculo(
placa			varchar(7) not null primary key,
idmodelo		int,
detalle			varchar(100),
mtc				varchar(20),
estado			int, -- 0ACTIVO 1INACTIVO 2TEMPORAL(se utilizó en un solo viaje)
foreign key (idmodelo) references tb_modelo_vehiculo(idmodelo)
);

create table tb_socio(
codsocio		int,
idempresa		int,
dnisocio		int,
nombresocio		varchar(70),
dniconductor	int,
placa			varchar(7),
foreign key (dniconductor) references tb_conductor(dniconductor),
foreign key (placa) references tb_vehiculo(placa),
primary key (codsocio, dnisocio)
);

create table tb_pasajero(
dnipasajero		int not null primary key,
ruc				varchar(11),
fnacimiento		date,
nombre			varchar(50),
razsocial		varchar(80),
nacionalidad	varchar(50),
direccion		varchar(80)
);

create table tb_sedes(
idsede			int primary key auto_increment,
sede			varchar(50)
);

create table tb_viaje(
nviaje			int primary key auto_increment,
codsocio		int,
empresa			int,
idorigen		int,
iddestino		int,
fpartida		datetime,
fllegada		datetime,
placa			varchar(7),
dniconductor	int,
prepasaje		float,
totpasajes		float,
totalasientos	int,
asientosven		int,
foreign key (codsocio) references tb_socio(codsocio),
foreign key (idorigen) references tb_sedes(idsede),
foreign key (iddestino) references tb_sedes(idsede),
foreign key (placa) references tb_vehiculo(placa),
foreign key (dniconductor) references tb_conductor(dniconductor)
);

create table tb_detalle_viaje(
nviaje			int,
nboleto			int,
dnipasajero		int,
asiento			int,
edad			int,
prepasaje 		float,
contratante		int,
primary key (nviaje, nboleto),
foreign key (nviaje) references tb_viaje(nviaje),
foreign key (dnipasajero) references tb_pasajero(dnipasajero)
);

create table tb_detalle_viaje_otros(
nviaje			int primary key,
standar			int, -- 0NO 1SI
escalacom		int, -- 0NO 1SI
ciudaddesde		varchar(50),
ciudadhasta		varchar(50),
puntoencuentro	varchar(60),
escalas			varchar(150),
dniconductor1	int,
horainicio1		varchar(5),
horainicio2		varchar(5),
dniconductor2	int,
horafin1		varchar(5),
horafin2		varchar(5),
modalidad		int,
totalmodif		float,
usuario			varchar(50),
foreign key (nviaje) 		references tb_viaje(nviaje)
);


create table tb_venta_temporal(
id				int not null primary key,
estado			int, -- 0Nuevo 1En creacion
codsocio		int,
empresa			int,  -- 0NULL 1MERMA 2SIGUEL
dniconductor	int,
placa			varchar(7),
modelovh		int,
idorigen		int, -- modificado
iddestino		int, -- modificado
fpartida		datetime,
fllegada		datetime,
prepasaje		float,
nviaje			int,
standar			int, -- 0NO 1SI
escalacom		int, -- 0NO 1SI	
ciudaddesde		varchar(50),
ciudadhasta		varchar(50),
puntoencuentro	varchar(60),
escalas			varchar(150),
horainicio2		varchar(5),
dniconductor2	int,
licencia2		varchar(30),
horafin1		varchar(5),
horafin2		varchar(5),
modalidad		int,
totalmodif		float,
usuario			varchar(50),
verificarInfAdi	int
);       

create table tb_pasajeros_temporal(
asiento 		int not null primary key,
estado			int, -- 0Libre 1Ocupado
nboleto			int,
dnipasajero		int,
edad			int,
prepasaje 		float,
contratante		int, -- 0NO 1SI
foreign key (dnipasajero) references tb_pasajero(dnipasajero)
);

create table tb_configuracion_inicial(
estado			int primary key, -- 0Iniciando por primera vez 
idsede			int,
nserie			varchar(3),
nviajeinicial	int,
nboletoinicial	int
);

create table tb_comprobantes_emitidos(
nserie1			varchar(10),
nserie2			varchar(10),
idempresa		int,
idorigen		int,
iddestino		int,
descripcion		varchar(200),
importe			float,
fecha			date,
comprobante		int, -- 1BOLETA 2FACTURA
primary key (nserie1,nserie2),
foreign key (idempresa) references tb_empresa(idempresa),
foreign key (idorigen) references tb_sedes(idsede),
foreign key (iddestino) references tb_sedes(idsede)
);

create table tb_gastos(
idgasto			int primary key auto_increment,
nserie1			varchar(10),
nserie2			varchar(10),
idempresa		int,
idorigen		int,
iddestino		int,
descripcion		varchar(200),
monto			float,
fecha			date,
foreign key (idempresa) references tb_empresa(idempresa)
);


insert into tb_usuario values('bxb', 'stand207', 'BYTE X BYTE', 2);
insert into tb_usuario values('alex', 'Aa123', 'Alexander Gamarra', 1);
insert into tb_usuario values('admin', 'admin', 'ADMINISTRADOR', 0);

insert into tb_empresa values(null, '20406468683', 'MERMA HERMANOS S.R.L.');
insert into tb_empresa values(null, '20601642124', 'ZIGUEL E.I.R.L.');

insert into tb_modelo_vehiculo values(null, 'Mercedes Sprinter 413 19+1', 20); -- 1
insert into tb_modelo_vehiculo values(null, 'Mercedes Sprinter 515 19+1', 20); -- 2
insert into tb_modelo_vehiculo values(null, 'Mercedes Sprinter 515 20+1', 21); -- 3	
insert into tb_modelo_vehiculo values(null, 'Renault Master 2012 15',     15); -- 4
insert into tb_modelo_vehiculo values(null, 'Renault Master Moderna 15',  15); -- 5
insert into tb_modelo_vehiculo values(null, 'Wolkswagen Crafter 20+1',    21); -- 6

insert into tb_sedes values(null, 'Arequipa');
insert into tb_sedes values(null, 'Juliaca');
insert into tb_sedes values(null, 'Espinar');
insert into tb_sedes values(null, 'Sicuani');

insert into tb_configuracion_inicial values(0, -1, null, 1, 1);

insert into tb_venta_temporal values(1, 0, 0, 0, 0, null, 0, 0, 0, null, null, 0, -1, 1, 0, null, null, null, null, null, 0, null, null, null, 0, -1, null);


insert into tb_vehiculo values('AAA-111', 1, 'Detalle1', 'MTC1', 0);
insert into tb_vehiculo values('BBB-222', 2, 'Detalle2', 'MTC2', 0);
insert into tb_vehiculo values('CCC-333', 3, 'Detalle3', 'MTC3', 0);
insert into tb_vehiculo values('DDD-444', 4, 'Detalle4', 'MTC4', 0);
insert into tb_vehiculo values('EEE-555', 5, 'Detalle5', 'MTC5', 0);
insert into tb_vehiculo values('FFF-666', 6, 'Detalle6', 'MTC6', 0);

insert into tb_conductor values(76782001, 'F76782001', 'Conductor1');
insert into tb_conductor values(76782002, 'F76782002', 'Conductor2');
insert into tb_conductor values(76782003, 'F76782003', 'Conductor3');
insert into tb_conductor values(76782004, 'F76782004', 'Conductor4');
insert into tb_conductor values(76782005, 'F76782005', 'Conductor5');
insert into tb_conductor values(76782006, 'F76782006', 'Conductor6');

insert into tb_socio values(1001, 1, 76781001, 'Socio1', 76782001, 'AAA-111');
insert into tb_socio values(1002, 2, 76781002, 'Socio2', 76782002, 'BBB-222');
insert into tb_socio values(1003, 1, 76781003, 'Socio3', 76782003, 'CCC-333');
insert into tb_socio values(1004, 2, 76781004, 'Socio4', 76782004, 'DDD-444');
insert into tb_socio values(1005, 1, 76781005, 'Socio5', 76782005, 'EEE-555');
insert into tb_socio values(1006, 2, 76781006, 'Socio6', 76782006, 'FFF-666');

-- EJECUTAR HASTA AQUÍ PARA QUE FUNCIONE EL SISTEMA


-- ELIMINAR TABLAS Y DB -----------------------------------------------------------
 drop database db_venta_pasajes; 
-- drop table tb_usuario;
-- drop table tb_conductor;
-- drop table tb_modelo_vehiculo;
-- drop table tb_vehiculo;
-- drop table tb_pasajero;
-- drop table tb_sedes;
-- drop table tb_viaje;
-- drop table tb_detalle_viaje;
-- drop table tb_detalle_viaje_otros;
-- drop table tb_venta_temporal;
-- drop table tb_pasajeros_temporal;
-- drop table tb_configuracion_inicial;
-- drop table tb_comprobantes_emitidos;
-- drop table tb_gastos;

-- SELECT TABLAS -----------------------------------------------------------------
use db_venta_pasajes;
select * from tb_usuario;
select * from tb_empresa;
select * from tb_conductor;
select * from tb_vehiculo;
select * from tb_socio;
select * from tb_modelo_vehiculo;
select * from tb_pasajero;
select * from tb_sedes;
select * from tb_viaje;
select * from tb_detalle_viaje;
select * from tb_detalle_viaje_otros;
select * from tb_venta_temporal;
select * from tb_pasajeros_temporal;
select * from tb_configuracion_inicial;
select * from tb_comprobantes_emitidos;
select * from tb_gastos;


show status like 'Threads%';

-- PRUEBAS ------------------------------------------------------------------------

alter table tb_vehiculo
  add codsocioantiguo int;

delete from tb_vehiculo where placa = 'BBB-222';

UPDATE tb_venta_temporal SET fpartida = concat(date(fpartida), ' 21:00:00') WHERE id=1;
UPDATE tb_venta_temporal SET modalidad = 1 WHERE id=1;
UPDATE tb_venta_temporal SET totalmodif = 54321 WHERE id=1;

-- BOLETA VENTA
select pt.dnipasajero, p.ruc, p.nombre, vt.idorigen, orgn.sede Origen, vt.iddestino, dstn.sede Destino, pt.prepasaje, vt.totalmodif, vt.escalas, p.direccion
from  tb_pasajeros_temporal pt
inner join tb_pasajero p on p.dnipasajero = pt.dnipasajero
inner join tb_venta_temporal vt on vt.id = 1
inner join tb_vehiculo vh on vh.placa = vt.placa
inner join tb_sedes orgn on orgn.idsede = vt.idorigen
inner join tb_sedes dstn on dstn.idsede = vt.iddestino
where pt.contratante = 1;

-- BOLETO DE VIAJE
select p.nombre, pt.dnipasajero, p.razsocial, p.ruc, vt.fpartida, pt.prepasaje, vt.idorigen, orgn.sede Origen, vt.iddestino, dstn.sede Destino, vt.nviaje, pt.nboleto
from tb_pasajeros_temporal pt
inner join tb_venta_temporal vt on vt.id = 1
inner join tb_pasajero p on p.dnipasajero = pt.dnipasajero
inner join tb_sedes orgn on orgn.idsede = vt.idorigen
inner join tb_sedes dstn on dstn.idsede = vt.iddestino
where pt.asiento = 3;

-- CONTRATO
select vt.placa, DATE_FORMAT(vt.fpartida, '%d-%m-%Y') Fecha_Inicio, TIME(vt.fpartida) Hora_Salida, p.dnipasajero, p.nombre, vt.idorigen, orgn.sede Origen, vt.iddestino, dstn.sede Destino, 
vt.puntoencuentro, vt.ciudaddesde, vt.ciudadhasta, mvh.casientos,  round(sum(pt2.prepasaje)) totpasajes, vt.modalidad, (ELT(WEEKDAY(vt.fpartida) + 1, 'LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO')) AS DIA_SEMANA
from tb_venta_temporal vt
inner join tb_sedes orgn on orgn.idsede = vt.idorigen
inner join tb_sedes dstn on dstn.idsede = vt.iddestino
inner join  tb_vehiculo vh on vh.placa = vt.placa
inner join  tb_modelo_vehiculo mvh on mvh.idmodelo = vh.idmodelo
inner join tb_conductor c on c.dniconductor = vt.dniconductor
left join tb_conductor c2 on c2.dniconductor = vt.dniconductor2
left join tb_pasajeros_temporal pt on pt.contratante = 1
left join tb_pasajeros_temporal pt2 on pt2.estado = 1
left join tb_pasajero p on pt.dnipasajero = p.dnipasajero
where  vt.id = 1;

-- HOJA DE RUTA
select vt.placa, DATE_FORMAT(vt.fpartida, '%d-%m-%Y') Fecha_Inicio, DATE_FORMAT(vt.fllegada, '%d-%m-%Y') Fecha_Llegada, vt.standar, vt.escalacom, vt.idorigen, orgn.sede Origen, vt.iddestino, dstn.sede Destino,  
TIME(vt.fpartida) Hora_Salida,  TIME(vt.fllegada) Hora_Llegada, c.conductor, c.licencia, TIME(vt.fpartida) Hora_Inicio_1, 
vt.horainicio2, c2.conductor c2, c2.licencia l2, vt.horafin1, vt.horafin2
from tb_venta_temporal vt
inner join tb_sedes orgn on orgn.idsede = vt.idorigen
inner join tb_sedes dstn on dstn.idsede = vt.iddestino
inner join tb_conductor c on c.dniconductor = vt.dniconductor
left join tb_conductor c2 on c2.dniconductor = vt.dniconductor2
where  vt.id = 1;

-- ITINERARIO
select vt.placa, DATE_FORMAT(vt.fpartida, '%d-%m-%Y') Fecha_Inicio, DATE_FORMAT(vt.fllegada, '%d-%m-%Y') Fecha_Llegada, p.dnipasajero, p.nombre,  vt.standar, vt.escalacom, vt.idorigen, orgn.sede Origen, vt.iddestino, dstn.sede Destino, 
TIME(vt.fpartida) Hora_Salida,  TIME(vt.fllegada) Hora_Llegada, c.conductor, c.licencia, TIME(vt.fpartida) Hora_Inicio_1, 
vt.horainicio2, c2.conductor c2, c2.licencia l2, vt.horafin1, vt.horafin2, vt.puntoencuentro, vt.escalas, vt.ciudaddesde, vt.ciudadhasta,  round(sum(pt2.prepasaje)) totpasajes
from tb_venta_temporal vt
inner join tb_sedes orgn on orgn.idsede = vt.idorigen
inner join tb_sedes dstn on dstn.idsede = vt.iddestino
inner join tb_conductor c on c.dniconductor = vt.dniconductor
left join tb_conductor c2 on c2.dniconductor = vt.dniconductor2
left join tb_pasajeros_temporal pt on pt.contratante = 1
left join tb_pasajeros_temporal pt2 on pt2.estado = 1
left join tb_pasajero p on pt.dnipasajero = p.dnipasajero
where  vt.id = 1;

-- MANIFIESTO DE PASAJEROS
select vt.idorigen, orgn.sede Origen, vt.iddestino, dstn.sede Destino, DATE_FORMAT(vt.fpartida, '%d-%m-%Y') Fecha_Viaje,  TIME(vt.fpartida) Hora_Salida, mvh.casientos, c.conductor, c.licencia, vh.placa, mvh.modelo, vh.mtc, pt.asiento, p.nombre, pt.dnipasajero, pt.edad, pt.nboleto, p.nacionalidad, pt.prepasaje, vt.nviaje
from  tb_pasajeros_temporal pt
inner join  tb_pasajero p on p.dnipasajero = pt.dnipasajero
inner join  tb_venta_temporal vt on  vt.id = 1
inner join tb_sedes orgn on orgn.idsede = vt.idorigen
inner join tb_sedes dstn on dstn.idsede = vt.iddestino
inner join  tb_conductor c on c.dniconductor = vt.dniconductor
inner join  tb_vehiculo vh on  vh.placa = vt.placa
inner join  tb_modelo_vehiculo mvh on mvh.idmodelo = vh.idmodelo
inner join  tb_empresa e on e.idempresa = vt.empresa
where pt.estado = 1 
order by pt.asiento;

use db_venta_pasajes;

-- VIAJES REALIZADOS
select v.nviaje, v.codsocio, s.nombresocio, v.dniconductor, c.conductor, v.placa, e.empresa, orgn.sede Origen, dstn.sede Destino, v.fpartida, v.fllegada, v.asientosven, v.totalasientos, v.prepasaje, v.totpasajes
from tb_viaje v
inner join tb_socio s 		on s.codsocio	= v.codsocio
inner join tb_empresa e 	on e.idempresa 	= v.empresa
inner join tb_sedes orgn 	on orgn.idsede 	= v.idorigen
inner join tb_sedes dstn 	on dstn.idsede 	= v.iddestino
inner join  tb_conductor c 	on c.dniconductor = v.dniconductor
where fpartida between '2019-10-24 00:00:00'  and '2019-10-26 23:59:59';

-- DETALLES DE VIAJE
select dv.nviaje, dv.asiento, dv.nboleto, dv.dnipasajero, p.nombre, dv.prepasaje, dv.contratante 
from tb_detalle_viaje dv
inner join tb_pasajero p 	on p.dnipasajero = dv.dnipasajero
where dv.nviaje = 1000
order by dv.asiento;

-- DETALLES VIAJE OTROS
select * from tb_detalle_viaje_otros dvo;
select dvo.standar, dvo.escalacom, dvo.ciudaddesde, dvo.ciudadhasta, dvo.puntoencuentro, dvo.escalas, dvo.dniconductor1, c1.conductor, dvo.horainicio1, dvo.horainicio2, dvo.dniconductor2,
c2.conductor, dvo.horafin1, dvo.horafin2, dvo.modalidad, dvo.totalmodif
from tb_detalle_viaje_otros dvo
inner join  tb_conductor c1	on c1.dniconductor = dvo.dniconductor1
inner join  tb_conductor c2	on c2.dniconductor = dvo.dniconductor2;

-- 
use db_colas;
select distinct ventanilla, case 	when estado = 2 then 'No atendidos'
									when estado = 3 then 'Atendidos'
									else null
                                    end as Detalle, count(*) from tb_colas 
where estado = 2 or estado = 3						
group by ventanilla,estado;

select distinct ventanilla, case 	when estado = 2 then 'No atendidos'
									when estado = 3 then 'Atendidos'
									else null
                                    end as Detalle, count(*), fecha from tb_colas
where 
fecha between  '2019-11-01'  and  '2019-12-12'
and (estado = 2 or estado = 3)
group by ventanilla,estado;


select * from tb_colas;


















