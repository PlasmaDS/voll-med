alter table `vollmed_api`.`pacientes` 
add column `urbanización` varchar(100) null after `activo`,
change column `codigopostal` `codigopostal` varchar(9) null ,
change column `complemento` `complemento` varchar(100) null ,
change column `numero` `numero` varchar(20) not null ,
change column `provincia` `provincia` varchar(100) null ;
