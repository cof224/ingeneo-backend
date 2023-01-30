insert into transporte.roles (name) values ('ROLE_ADMIN');
insert into transporte.roles (name) values ('ROLE_USER');
insert into transporte.roles (name) values ('ROLE_MODERATOR');

INSERT INTO transporte.warehouses( name, type, enabled) VALUES ( 'La Esperanza', 1, true);
INSERT INTO transporte.warehouses( name, type, enabled) VALUES ( 'El Porvenir', 2, true);

INSERT INTO transporte.products(name, description, price, enabled) VALUES ('Laptop', '', 500, true);
INSERT INTO transporte.products(name, description, price, enabled) VALUES ('Impresora', '', 200, true);
INSERT INTO transporte.products(name, description, price, enabled) VALUES ('Escritorio', '', 100, true);
INSERT INTO transporte.products(name, description, price, enabled) VALUES ('Cargador', '', 50, true);

INSERT INTO transporte.services(name, description) VALUES ( 'Terrestre','Logistica Terrestre' );
INSERT INTO transporte.services(name, description) VALUES ( 'MAritima','Logistica Maritima' );