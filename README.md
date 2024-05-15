# Nave Espacial API

Este proyecto proporciona una API RESTful para gestionar naves espaciales. La API incluye funcionalidades para crear, leer, actualizar y eliminar (CRUD) naves espaciales, con seguridad básica integrada.

## Documentación Javadoc

Abrir en navegador.
- [API JAVADOC](API/allclasses-index.html).

## Endpoints

### Obtener Todas las Naves Espaciales
**Método:** `GET`  
**Ruta:** `/api/naves`  
**Descripción:** Obtiene todas las naves espaciales con paginación.  
**Parámetros:**
1. **pageable** - Información de paginación.
- **Retorno:** Página de modelos de naves espaciales.

**Ejemplo de Petición en Postman**

HTTP:
```http
GET /api/naves?page=0&size=10
```

Bash:
```bash
curl -X GET "http://localhost:8080/api/naves?page=0&size=10"
```

### Obtener Nave Espacial por ID
**Método:** `GET`  
**Ruta:** `/api/naves/{id}`  
**Descripción:** Obtiene una nave espacial por su ID.  
**Parámetros:**
1. **id** - Identificador de la nave espacial.
- **Retorno:** Respuesta con el modelo de la nave espacial si se encuentra, o un estado 404 si no.

**Ejemplo de Petición en Postman**

HTTP:
```http
GET /api/naves/1
```

Bash:
```bash
curl -X GET "http://localhost:8080/api/naves/1"
```

### Buscar Naves Espaciales por Nombre
**Método:** `GET`  
**Ruta:** `/api/naves/buscar`  
**Descripción:** Busca naves espaciales por nombre.  
**Parámetros:**
1. **nombre** - Nombre de la nave espacial.
- **Retorno:** Lista de modelos de naves espaciales que coinciden con el nombre.

**Ejemplo de Petición en Postman**

HTTP:
```http
GET /api/naves/buscar?nombre=Enterprise
```

Bash:
```bash
curl -X GET "http://localhost:8080/api/naves/buscar?nombre=Enterprise"
```

### Crear Nave Espacial
**Método:** `POST`  
**Ruta:** `/api/naves`  
**Descripción:** Crea una nueva nave espacial.  
**Parámetros:**
- **naveEspacial** - Modelo de la nueva nave espacial.
- **Retorno:** Respuesta con el modelo de la nave espacial creada y un estado 201.

**Ejemplo de Petición en Postman**

HTTP:
```http
POST /api/naves
Content-Type: application/json

{
  "nombre": "Enterprise",
  "serie": "Star Trek",
  "pelicula": "The Wrath of Khan"
}
```

Bash:
```bash
curl -X POST "http://localhost:8080/api/naves" \
    -H "Content-Type: application/json" \
    -d '{"nombre": "Enterprise", "serie": "Star Trek", "pelicula": "The Wrath of Khan"}'
```

### Actualizar Nave Espacial
**Método:** `PUT`  
**Ruta:** `/api/naves/{id}`  
**Descripción:** Actualiza una nave espacial existente.  
**Parámetros:**
1. **id** - Identificador de la nave espacial.
2. **naveEspacial** - Modelo de la nave espacial actualizada.
- **Retorno:** Respuesta con el modelo de la nave espacial actualizada.

**Ejemplo de Petición en Postman**

HTTP:
```http
PUT /api/naves/1
Content-Type: application/json

{
  "nombre": "Enterprise",
  "serie": "Star Trek",
  "pelicula": "The Search for Spock"
}
```

Bash:
```bash
curl -X PUT "http://localhost:8080/api/naves/1" \
    -H "Content-Type: application/json" \
    -d '{"nombre": "Enterprise", "serie": "Star Trek", "pelicula": "The Search for Spock"}'
```

### Eliminar Nave Espacial
**Método:** `DELETE`  
**Ruta:** `/api/naves/{id}`  
**Descripción:** Elimina una nave espacial por su ID.  
**Parámetros:**
1. **id** - Identificador de la nave espacial.
- **Retorno:** Respuesta con un estado 204 si la eliminación es exitosa.

**Ejemplo de Petición en Postman**

HTTP:
```http
DELETE /api/naves/1
```

Bash:
```bash
curl -X DELETE "http://localhost:8080/api/naves/1"
```
