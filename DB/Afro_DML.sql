
INSERT INTO tarjeta (id,tipo, descripcion) VALUES (0,'baja','Entrega Persona B');
INSERT INTO tarjeta (id,tipo, descripcion) VALUES (1,'alta','Familia Landa');

INSERT INTO persona(id,nombre, apellido1, apellido2, fnac, genero,nacionalidad, direccion, divfuncional, fllegada, telefono, formacion, TIS, docIdentidad,tipoDocIdentidad, derivacion,fcaducidad, comentarios, idtarjeta, estcivil, trasocial) 
            VALUES (1,'Aitor', 'Landa', 'Arrue','1998/05/10','h','España',NULL, NULL ,'2019/05/10', 915681255, NULL ,NULL,'15738241W','DNI','Ayuntamiento de Vitoria', '2019/12/10',NULL, 1,'Soltero',TRUE);
INSERT INTO persona (id,nombre, apellido1, apellido2, fnac, genero,nacionalidad, direccion, divfuncional, fllegada, telefono, formacion, TIS, docIdentidad,tipoDocIdentidad, derivacion,fcaducidad, comentarios, idtarjeta, estcivil, trasocial) 
VALUES (2,'Taylor', 'Landa', 'Vans','2001/05/10','x','Croacia',NULL, NULL ,'2019/05/10', 915686655, NULL ,NULL,'157382YTZ','DNI','Ayuntamiento de Vitoria', '2019/12/10',NULL, 1,'Soltero',TRUE);			
INSERT INTO persona (id,nombre, apellido1, apellido2, fnac, genero,nacionalidad, direccion, divfuncional, fllegada, telefono, formacion, TIS, docIdentidad,tipoDocIdentidad, derivacion,fcaducidad, comentarios, idtarjeta, estcivil, trasocial) 
VALUES (3,'Xiomara', 'Rodriguez', 'Menendez','1985/05/10','x','Peru',NULL, NULL ,'2019/05/20', 915686335, NULL ,NULL,'157382K','DNI','Diputacion de Gipuzkoa', '2020/11/10',NULL, 1,'Soltero',TRUE);			
	
INSERT INTO altas_bajas (idPersona, falta, fbaja) VALUES (1, '2019/05/10', NULL);
INSERT INTO altas_bajas (idPersona, falta, fbaja)  VALUES (2, '2019/05/10', NULL);
INSERT INTO altas_bajas (idPersona, falta, fbaja)  VALUES (3, '2019/05/20', NULL);

INSERT INTO persona_b (id ,nombre, apellidos, nacionalidad) VALUES (1, 'Jaime', 'Peña', 'Chile');

INSERT INTO entrega (idtarjeta, fecha, idPB) VALUES (1, '2019/06/03', NULL );
INSERT INTO entrega (idtarjeta, fecha, idPB) VALUES (0, '2019/06/03', 1);
 	
INSERT INTO progenitores(idpadre, idhijo) VALUES (1,2);

INSERT INTO empleado(id,nombre, apellido1, apellido2, tipo, fnac, docIdentidad, tipoDocIdentidad, estudios, telefono, email, puesto, sueldo,loginuser,loginpass)
				VALUES (1,'Blanca', 'Arrue', 'Landa', 'Voluntario','1969/05/10','12121212A','DNI', 'Pedagogia',915681255,'blanca@gmail.com', NULL,  NULL,'blanca','password');
INSERT INTO empleado(id,nombre, apellido1, apellido2, tipo, fnac, docIdentidad, tipoDocIdentidad, estudios, telefono, email, puesto, sueldo,loginuser,loginpass) 
VALUES (2,'Andoni', 'Agirre', NULL, 'Trabajador','1985/05/14', '23232323B', 'DNI', 'Psicologia',9156456255,'andoni@afro.eus', 'Psicologo', 999, 'andoni','password');
				
INSERT INTO actividad(id,nombre, descripcion, plazas, fini, ffin, idcoord) 
            VALUES (1,'Clase de Euskera', 'Clases para aprender Euskera', 20,'2019/05/10', '2019/10/10', 1);
INSERT INTO actividad(id,nombre, descripcion, plazas, fini, ffin, idcoord)  VALUES (2,'Clase de Castellano', 'Clases para aprender Castellano', 31,'2019/01/10', '2019/10/12', 1);
INSERT INTO actividad(id,nombre, descripcion, plazas, fini, ffin, idcoord)  VALUES (3,'Clase de Leer y Escribir', 'Clases para aprender a leer y escribir',35,'2019/03/10', '2019/10/11', 1);
				
INSERT INTO act_impartidor(idact,idemp,fini,ffin) VALUES (1,1, '2019/06/01','2019/10/10');

INSERT INTO participante(idper, idact, fini,ffin) VALUES (2,1,'2019/07/01', NULL);

INSERT INTO institucion(id,nombre) VALUES (1,'Union Europea');
INSERT INTO institucion (id,nombre) VALUES (2,'Ayuntamiento de Vitoria');
INSERT INTO institucion (id,nombre) VALUES (3,'Diputacion de Alava');

INSERT INTO piso(id, nomPrograma, direccion, aforo, tipo, intensidad, telefono) VALUES (1,'Etorkizun','Goiru,2', 10, 'mixto', 'alta', 616973780);
INSERT INTO piso (id, nomPrograma, direccion, aforo, tipo, intensidad, telefono) VALUES (2,'Amaia','Loramendi,4', 7, 'Solo Mujeres', 'baja', 123456728);

INSERT INTO subvencion(idpiso,idins,porcentaje,fini,ffin) VALUES (1,1,15,'2019/05/10','2020/05/10');
INSERT INTO subvencion (idpiso,idins,porcentaje,fini,ffin) VALUES (2,2,35,'2019/02/10','2019/12/10');

INSERT INTO persona_piso(idper,idpiso,fentrada,fsalida) VALUES (1,1,'2019/01/30', NULL);
INSERT INTO persona_piso(idper,idpiso,fentrada,fsalida) VALUES (2,1,'2019/01/30', NULL);
INSERT INTO persona_piso(idper,idpiso,fentrada,fsalida) VALUES (3,2,'2019/05/30', NULL);

INSERT INTO turno(idpiso, idemp, fecha, tur) VALUES (1,1, '2019/06/10', 'mañana');
INSERT INTO turno (idpiso, idemp, fecha, tur) VALUES (2,2, '2019/06/10', 'tarde');
INSERT INTO turno (idpiso, idemp, fecha, tur) VALUES (2,1, '2019/06/10', 'noche');



