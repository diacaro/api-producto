CREATE TABLE IF NOT EXISTS  products (
    id serial,
    description VARCHAR(45) NOT NULL,
    details VARCHAR(45) NULL,
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

CREATE TABLE IF NOT EXISTS  orden (
    id serial,
    id_cliente int not null,
    id_producto int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (id_cliente) REFERENCES client(id),
    FOREIGN KEY (id_producto) REFERENCES products(id)
    );

