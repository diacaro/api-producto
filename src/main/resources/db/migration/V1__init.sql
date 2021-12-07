CREATE TABLE IF NOT EXISTS  products (
  id serial,
  description VARCHAR(45) NOT NULL,
  details VARCHAR(45) NULL,
  PRIMARY KEY (id)
  );

  CREATE TABLE IF NOT EXISTS  orden (
    id serial,
    idCliente VARCHAR(45) NOT NULL,
    idOrden VARCHAR(45) NULL,
    PRIMARY KEY (id)
    );

  CREATE TABLE IF NOT EXISTS  client (
    id serial,
    nombre VARCHAR(45) NOT NULL,
    ci VARCHAR(45) NULL,
    PRIMARY KEY (id)
    );
  CREATE TABLE IF NOT EXISTS  proveedor (
    id serial,
    nombre VARCHAR(45) NOT NULL,
    telefono VARCHAR(45) NOT NULL,
    categoria VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
    );