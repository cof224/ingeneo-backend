CREATE SCHEMA IF NOT EXISTS transporte;

CREATE TABLE IF NOT EXISTS transporte.users
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    email VARCHAR(255) ,
    password VARCHAR(255) ,
    username VARCHAR(255) ,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT email_ukey UNIQUE (email),
    CONSTRAINT username_ukey UNIQUE (username)
    );
COMMENT ON COLUMN transporte.users.id IS 'identificador unico del usuario';	
COMMENT ON COLUMN transporte.users.email IS 'correo electronico del usuario';	
COMMENT ON COLUMN transporte.users.password IS 'contrasenia del usuario';	
COMMENT ON COLUMN transporte.users.username IS 'usuario';	

CREATE TABLE IF NOT EXISTS transporte.roles
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    name VARCHAR(20) ,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
    );

COMMENT ON COLUMN transporte.roles.id IS 'identificador unico del rol';	
COMMENT ON COLUMN transporte.roles.name IS 'nombre del rol que se asociara a un usuario especifico';	
	

CREATE TABLE IF NOT EXISTS transporte.user_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    FOREIGN KEY (user_id)
    REFERENCES transporte.users (id),
    FOREIGN KEY (role_id)
    REFERENCES transporte.roles (id)
    );
COMMENT ON COLUMN transporte.user_roles.user_id IS 'identificador unico del usuario';	
COMMENT ON COLUMN transporte.user_roles.role_id IS 'identificador unico del rol';	



CREATE TABLE IF NOT EXISTS transporte.warehouses
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL,
    type integer NOT NULL,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT warehouses_pkey PRIMARY KEY (id)
    );
COMMENT ON COLUMN transporte.warehouses.id IS 'identificador unico del almacen';	
COMMENT ON COLUMN transporte.warehouses.name IS 'nombre del almacen';	
COMMENT ON COLUMN transporte.warehouses.type IS 'tipo de almacen';	
COMMENT ON COLUMN transporte.warehouses.enabled IS 'bandera que indica si el almacen esta habilitado';	


CREATE TABLE IF NOT EXISTS transporte.products
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
    name VARCHAR(100)  NOT NULL,
    description VARCHAR(100) ,
    price NUMERIC(10,4) NOT NULL DEFAULT 0,
    enabled boolean NOT NULL DEFAULT true,
    CONSTRAINT products_pkey PRIMARY KEY (id)
    );
COMMENT ON COLUMN transporte.products.id IS 'identificador unico del producto';	
COMMENT ON COLUMN transporte.products.name IS 'nombre del producto';	
COMMENT ON COLUMN transporte.products.description IS 'descripcion del prodcuto';	
COMMENT ON COLUMN transporte.products.price IS 'precio del producto';	
COMMENT ON COLUMN transporte.products.enabled IS 'bandera que indica si el producto esta habilitado para su envio';	

CREATE TABLE IF NOT EXISTS transporte.customers
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
    name VARCHAR(50)  NOT NULL,
	email VARCHAR(100)  NOT NULL,
    phone VARCHAR(8)  NOT NULL,
    address VARCHAR(100)  NOT NULL,
    active boolean DEFAULT true,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
    );
COMMENT ON COLUMN transporte.customers.id IS 'identificador unico del cliente';	
COMMENT ON COLUMN transporte.customers.name IS 'nombre del cliente';	
COMMENT ON COLUMN transporte.customers.email IS 'correo electronico del cliente';	
COMMENT ON COLUMN transporte.customers.phone IS 'telefono del cliente';	
COMMENT ON COLUMN transporte.customers.address IS 'direccion del cliente';	
COMMENT ON COLUMN transporte.customers.active IS 'indica si esta activo el cliente';	
COMMENT ON COLUMN transporte.customers.created_date IS 'fecha de registro del cliente';	



CREATE TABLE IF NOT EXISTS transporte.services
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    name VARCHAR(50)  NOT NULL,
    description VARCHAR(100) ,
    CONSTRAINT services_pkey PRIMARY KEY (id));
COMMENT ON COLUMN transporte.services.id IS 'identificador unico del servicio';	
COMMENT ON COLUMN transporte.services.name IS 'nombre del servicio';	
COMMENT ON COLUMN transporte.services.description IS 'descripcion del servicio';



CREATE TABLE IF NOT EXISTS transporte.shipments
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
    customer_id bigint NOT NULL,
	warehouse_id bigint NOT NULL,
    product_id bigint NOT NULL,
	service_id bigint NOT NULL,
	quantity integer NOT NULL,
    shipment_date timestamp with time zone NOT NULL,
    discount numeric(10,4) NOT NULL,
    total numeric(10,4) NOT NULL,
    tracking_number VARCHAR(50)  NOT NULL,
	transport_number VARCHAR(10) NOT NULL,
    enabled boolean NOT NULL DEFAULT true,
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT shipments_pkey PRIMARY KEY (id),
    FOREIGN KEY (customer_id)
    REFERENCES transporte.customers (id),
    FOREIGN KEY (product_id)
    REFERENCES transporte.products (id),
    FOREIGN KEY (warehouse_id)
    REFERENCES transporte.warehouses (id),
    FOREIGN KEY (service_id)
    REFERENCES transporte.services (id)
    );
COMMENT ON COLUMN transporte.shipments.id IS 'identificador unico del envio';	
COMMENT ON COLUMN transporte.shipments.customer_id IS 'llave foranea del cliente';	
COMMENT ON COLUMN transporte.shipments.warehouse_id IS 'llave foranea del almacen';	
COMMENT ON COLUMN transporte.shipments.product_id IS 'llave foranea del producto';	
COMMENT ON COLUMN transporte.shipments.service_id IS 'llave foranea del servicio';	
COMMENT ON COLUMN transporte.shipments.quantity IS 'cantidad de producto enviada';	
COMMENT ON COLUMN transporte.shipments.shipment_date IS 'fecha de envio';	
COMMENT ON COLUMN transporte.shipments.discount IS 'descuento al que se hace acreedor el cliente';	
COMMENT ON COLUMN transporte.shipments.total IS 'total en dolares del cliente';	
COMMENT ON COLUMN transporte.shipments.tracking_number IS 'numero de guia';	
COMMENT ON COLUMN transporte.shipments.transport_number IS 'numero de placa o flota dependiendo del tipo de servicio';	
COMMENT ON COLUMN transporte.shipments.enabled IS 'indica si esta activo el envio';	
COMMENT ON COLUMN transporte.shipments.created_date IS 'fecha de registro del envio';	