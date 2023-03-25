# API User
##### Desarrollo ejercicio entrevista técnica

******

## Introducción

Este desarrollo es un API Rest que permite realizar un CRUD de usuarios, esta implementado con tecnología Spring Boot 2.6.6, Maven, Java 1.8, Swagger UI, Authentication OAuth2 Token
para acceder a los recursos del API y BD embebida H2.

Una vez clonado el proyecto usuario API User, ejecutar y acceder a la url:

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

## Endpoints

El sistema cuenta con los siguientes endpoints:

#### API User

Servicios REST CRUD de usuarios

```
PUT     | /api/user/created
GET     | /api/user/get
POST    | /api/user/change
DELETE  | /api/user/delete
```

## Mensaje de entrada

Para crear un usuario

#### created:

```json
{
  "name": "Juan Gallardo",
  "email": "jgallardo@test.cl",
  "password": "aaBB@@88",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
```

Para modificar un usuario

#### change:

```json
{
  "id": 1,
  "name": "Juan Gallardo",
  "email": "jgallardo@test.com",
  "password": "aaBB@@99",
  "isactive": false,
  "phones": [
    {
      "id": 1,
      "number": "7654321",
      "citycode": "2",
      "contrycode": "58"
    }
  ]
}
```

Para buscar o eliminar usuario, por su id de usuario.


## Para Autenticarse en la API User

Se debe utilizar el proyecto adicional AuthenticationServer, que permite generar un token de acceso a los recursos del API User, puedes bajarlo en el siguiente
link

https://github.com/demoanarquico/AuthServerUser.git

Una vez clonado, ejecutar con tu IDE de preferencia, y abrir la consola H2, en el puerto 9028

```
localhost:9028/h2-console/
```

Configurar:

Driver Class: org.hsqldb.jdbcDriver

JDBC URL: jdbc:hsqldb:mem:testdb

User Name: sa

Password:

A continuación utilizar los archivos que vienen en la carpeta doc del proyecto usuario API User.

Ejecutar Script query_auth_server.sql para crear las credenciales y roles del AuhtServer en la consola H2.

Ahora usamos la colección Postman AuthServerUser, para solicitar token.

Una vez solicitado el token, usar para consumir los recursos del API User.

&nbsp;
