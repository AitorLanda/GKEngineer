DROP TABLE persona CASCADE;
DROP TABLE altas_bajas CASCADE;
DROP TABLE persona_b CASCADE;
DROP TABLE tarjeta CASCADE;
DROP TABLE entrega CASCADE;
DROP TABLE piso CASCADE;
DROP TABLE persona_piso CASCADE;
DROP TABLE subvencion CASCADE;
DROP TABLE institucion CASCADE;
DROP TABLE turno CASCADE;
DROP TABLE actividad CASCADE;
DROP TABLE participante CASCADE;
DROP TABLE act_impartidor CASCADE;
DROP TABLE progenitores CASCADE;
DROP TABLE empleado CASCADE;

CREATE TABLE persona(
    id INT,
    nombre VARCHAR(50) NOT NULL,
    apellido1 VARCHAR(25),
    apellido2 VARCHAR(25),
    fnac DATE,
    genero CHAR(1),
    nacionalidad VARCHAR(25) NOT NULL,
    direccion VARCHAR(128),
    divfuncional TEXT,
    fllegada DATE,
    telefono BIGINT UNIQUE,
    formacion TEXT,
    TIS BIGINT,
    docIdentidad VARCHAR(25),
    tipoDocIdentidad VARCHAR(25),
    derivacion VARCHAR(25),
    fcaducidad DATE,
    comentarios TEXT,
    idtarjeta INT,
    estcivil VARCHAR(20),
    trasocial BOOLEAN);
                
CREATE TABLE altas_bajas(
    idPersona INT,
    falta DATE,
    fbaja DATE);

CREATE TABLE persona_b(
    id INT,
    nombre VARCHAR(50),
    apellidos VARCHAR(25),
    nacionalidad VARCHAR(25));

CREATE TABLE tarjeta(
    id INT,
    tipo VARCHAR(25),
    descripcion VARCHAR(128));

CREATE TABLE entrega(
    idtarjeta INT,
    fecha DATE,
    idPB int);

CREATE TABLE progenitores(
    idpadre INT,
    idhijo INT);

CREATE TABLE piso(
    id INT,
    nomPrograma VARCHAR(25),
    direccion VARCHAR(128),
    aforo INT,
    tipo VARCHAR(25),
    intensidad VARCHAR(10),
    telefono BIGINT UNIQUE);

CREATE TABLE persona_piso(
    idper INT,
    idpiso INT,
    fentrada DATE,
    fsalida DATE);

CREATE TABLE subvencion(
    idpiso INT,
    idins INT,
    porcentaje INT,
    fini DATE,
    ffin DATE);

CREATE TABLE  institucion(
    id INT,
    nombre VARCHAR(25));

CREATE TABLE turno(
    idpiso INT,
    idemp INT,
    fecha DATE,
    tur VARCHAR(10));

CREATE TABLE actividad(
    id INT,
    nombre VARCHAR(25),
    descripcion TEXT,
    plazas INT,
    fini DATE,
    ffin DATE,
    idcoord INT);

CREATE TABLE participante(
    idper INT,
    idact INT,
    fini DATE,
    ffin DATE);

CREATE TABLE act_impartidor(
    idact INT,
    idemp INT,
    fini DATE,
    ffin DATE);

CREATE TABLE empleado(
    id INT,
    nombre VARCHAR(50),
    apellido1 VARCHAR(25),
    apellido2 VARCHAR(25),
	nacionalidad VARCHAR(25),
    tipo VARCHAR(25),
    fnac DATE,
	docIdentidad VARCHAR(25),
    tipoDocIdentidad VARCHAR(25),
    estudios TEXT,
    telefono BIGINT UNIQUE,
    email VARCHAR(50),
    puesto VARCHAR(25),
	sueldo BIGINT,
	loginuser VARCHAR(50),
	loginpass VARCHAR(50));

ALTER TABLE tarjeta
 ADD CONSTRAINT tarjeta_pk PRIMARY KEY (id);

ALTER TABLE persona
 ADD CONSTRAINT persona_PK PRIMARY KEY (id),
 ADD CONSTRAINT persona_FK FOREIGN KEY (idtarjeta) REFERENCES tarjeta (id);

ALTER TABLE persona_b
 ADD CONSTRAINT persona_b_pk PRIMARY KEY (id);

ALTER TABLE progenitores
 ADD CONSTRAINT progenitores_pk PRIMARY KEY (idpadre, idhijo),
 ADD CONSTRAINT progenitores_fk FOREIGN KEY (idpadre) REFERENCES persona(id),
 ADD CONSTRAINT progenitores_fk1 FOREIGN KEY (idhijo) REFERENCES persona(id);

ALTER TABLE entrega
 ADD CONSTRAINT entrega_pk PRIMARY KEY (idtarjeta, fecha),
    ADD CONSTRAINT entrega_fk FOREIGN KEY (idtarjeta) REFERENCES tarjeta(id),
 ADD CONSTRAINT entrega_fk1 FOREIGN KEY (idpb) REFERENCES persona_b(id);

ALTER TABLE empleado
 ADD CONSTRAINT empleado_pk PRIMARY KEY (id);

ALTER TABLE actividad
 ADD CONSTRAINT actividad_pk PRIMARY KEY (id),
 ADD CONSTRAINT actividad_fk FOREIGN KEY (idcoord) REFERENCES empleado(id);

ALTER TABLE participante
 ADD CONSTRAINT participante_pk PRIMARY KEY (idper, idact),
 ADD CONSTRAINT participante_fk FOREIGN KEY (idper) REFERENCES persona(id),
 ADD CONSTRAINT participante_fk1 FOREIGN KEY (idact) REFERENCES actividad(id);

ALTER TABLE act_impartidor
 ADD CONSTRAINT impartidor_pk PRIMARY KEY (idact, idemp),
 ADD CONSTRAINT impartidor_fk FOREIGN KEY (idemp) REFERENCES empleado(id),
 ADD CONSTRAINT impartidor_fk1 FOREIGN KEY (idact) REFERENCES actividad(id);

ALTER TABLE piso
 ADD CONSTRAINT piso_pk PRIMARY KEY (id);

ALTER TABLE turno
 ADD CONSTRAINT turno_pk PRIMARY KEY (idpiso, idemp),
 ADD CONSTRAINT turno_fk FOREIGN KEY (idpiso) REFERENCES piso(id),
 ADD CONSTRAINT turno_fk1 FOREIGN KEY (idemp) REFERENCES empleado(id);

ALTER TABLE persona_piso
ADD CONSTRAINT persona_piso_pk PRIMARY KEY (idper, idpiso),
 ADD CONSTRAINT persona_piso_fk FOREIGN KEY (idper) REFERENCES persona(id),
 ADD CONSTRAINT persona_piso_fk1 FOREIGN KEY (idpiso) REFERENCES piso(id);

ALTER TABLE institucion
 ADD CONSTRAINT instituciones_pk PRIMARY KEY (id);

ALTER TABLE subvencion
 ADD CONSTRAINT subvencion_pk PRIMARY KEY (idpiso, idins),
 ADD CONSTRAINT subvencion_fk FOREIGN KEY (idpiso) REFERENCES piso(id),
 ADD CONSTRAINT subvencion_fk1 FOREIGN KEY (idins) REFERENCES institucion(id);
    
ALTER TABLE altas_bajas
 ADD CONSTRAINT altas_bajas_pk PRIMARY KEY (idpersona,falta),
 ADD CONSTRAINT altas_bajas_fk FOREIGN KEY (idpersona) REFERENCES persona(id);
  


  
