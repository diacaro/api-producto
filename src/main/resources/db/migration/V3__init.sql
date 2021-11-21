CREATE TABLE IF NOT EXISTS  proveedor (
  id serial,
  nombre VARCHAR(45) NOT NULL,
  telefono VARCHAR(45) NOT NULL,
  categoria VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
  );