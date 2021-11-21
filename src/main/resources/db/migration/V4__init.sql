  CREATE TABLE IF NOT EXISTS  consulta (
    id serial,
    cliente_idClient int NOT NULL,
    producto_idProduct int NOT NULL,
    proveedor_idProveedor int NOT NULL,
    fecha_consulta DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cliente_idClient) REFERENCES client(id),
    FOREIGN KEY (producto_idProduct) REFERENCES products(id),
    FOREIGN KEY (proveedor_idProveedor) REFERENCES proveedor(id)

    );