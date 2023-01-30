## Prueba Ingeneo
concursante : Cristian Fuentes

------------
####Tecnologias utilizadas

* Proyecto Maven
* Java (version 11)
* framework: spring boot version 2.7.8
* Base de datos: PostgreSQL
* documentacion de los  endpoint : swagger 3.0
* despliegue: railway url: https://ingeneo-backend-production.up.railway.app/

#### Listado de EndPoint's
#### para todos los endpoints es necesario una autenticacion full, exceptuando los metodos de listas
* /api/auth/signup ; endpoint para crear un usuarios con listado de roles: admin, user
* /api/auth/signin : endpoint para la autenticación de usuarios (utilizando Bearer Token).
* /api/test/customer : gestion de clientes, rol de acceso : USER
* /api/test/product: gestion de catalogos de productos, rol de acceso : USER.
* /api/test/warehouse: gestion de depositos (bodegas y puertos), rol de acceso : USER.
* /api/test/service: catálogo de tipo de servicio (1 : logistica terrestre, 2:logistica maritima ), rol de acceso USER.
* /api/test/warehouse, administración de puertos y/o bodegas.
* /api/test/shipment, gestion para los planes de entrega.


#####Documentacion de las APIs
https://ingeneo-backend-production.up.railway.app/swagger-ui/

![AUTH](AUTH-APIs.png)





Para todos los endpoint del tipo CRUD se crearan los siguientes cinco tipos de operaciones 

* /all  = Método HTTP: GET : returna un listado de la entidades
* /     = Método HTTP: POST : crear una entidad
* /{id} = Método HTTP: GET : devuelve una entidad en base a su identificador
* /{id} = Método HTTP: PUT: actualiza una entidad
* /{id} = Método HTTP: DELETE : elimina una entidad.

####Base de datos Postgres
```properties
PROD_DB_HOST=containers-us-west-76.railway.app
PROD_DB_PORT=7010
PROD_DB_NAME=railway
PROD_DB_PASSWORD=K9isWltVnkXWLUxbyF5K
PROD_DB_USERNAME=postgres
```


####Base de datos (Diagrama E-R)
![Diagrama ER](DIAGRAMA-ER.png)


####Seguridad <br>
######Crear Usuario desde Swagger

curl -X POST "https://ingeneo-backend-production.up.railway.app:443/api/auth/signup" -H "accept: */*" -H "Content-Type: application/json" -d "{\"email\":\"cf@gmail.com\",\"password\":\"123456\",\"role\":[\"ADMIN\"],\"username\":\"admin\"}"

######Login 
curl -X POST "https://ingeneo-backend-production.up.railway.app:443/api/auth/signin" -H "accept: */*" -H "Content-Type: application/json" -d "{\"password\":\"123456\",\"username\":\"admin\"}"

	
Respuesta
{
  "id": 1,
  "username": "admin",
  "email": "cf@gmail.com",
  "roles": [
    "ROLE_USER"
  ],
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY3NTAyMzg5NSwiZXhwIjoxNjc1MTEwMjk1fQ.pdntwzOsis6W40mG72usxIYCelvocJVG8RL9-GRD3GkWr5uHo69P6-0OZuPVwoySDuaRUMU9K7k5gDsR4K3Wcg",
  "tokenType": "Bearer"
}


Ejemplo de Autenticación de Usuarios:

curl --location --request POST 'https://ingeneo-backend-production.up.railway.app/api/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
"username":"admin",
"password":"123456"
}'

Ejemplo de uso de token para la creación un cliente:

curl --location --request POST 'https://ingeneo-backend-production.up.railway.app/api/test/customer/' \
--header 'Authorization: Bearer token' \
--header 'Content-Type: application/json' \
--data-raw '{
"active": true,
"address": "Colonia Flor Blanca",
"created": "2023-01-28T18:10:25.398Z",
"email": "Cristian.Fuentes@gmail.com",
"name": "Cristian Fuentes",
"phone": "22548495"
}'


