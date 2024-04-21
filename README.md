# Inditex Caso Práctico

## _Endpoint de consulta para base de datos de precios_

![Java version](https://img.shields.io/badge/JDK-17%20preview-ED8B00?logo=openjdk&logoColor=white&style=for-the-badge)

# Indice
- [Enunciado](#enunciado)
    - [Base de Datos de Comercio Electrónico](#base-de-datos-de-comercio-electrónico)
    - [Requisitos del Servicio](#requisitos-del-servicio)
    - [Tabla PRICES](#tabla-prices)
    - [Desarrollo de Tests para el Endpoint REST](#desarrollo-de-tests-para-el-endpoint-rest)
- [Diseño y Arquitectura](#diseño-y-arquitectura)
    - [Bounded Context](#bounded-context-pricing)
        - [Aggregate](#aggregate-products)
    - [Capa de Dominio](#capa-de-dominio)
    - [Capa de Aplicación](#capa-de-aplicación)
    - [Capa de Infraestructura](#capa-de-infraestructura)
- [Técnicas](#técnicas)
    - [TDD Outside-in y Honourable Retreat](#tdd-outside-in-y-honourable-retreat)
- [Caso de Uso Implementado y Diseño de URL](#caso-de-uso-implementado-y-diseño-de-url)
    - [Endpoint para Consultar Precios](#endpoint-para-consultar-precios)
    - [Justificación del Diseño de URL](#justificación-del-diseño-de-url)
- [Levantar el Servidor](#levantar-el-servidor)
    - [Docker](#docker)
    - [Maven](#maven)
## Enunciado
### Base de Datos de Comercio Electrónico

Este proyecto implica el desarrollo de una aplicación o servicio utilizando SpringBoot para proporcionar un endpoint REST que permita consultar precios. La tabla `PRICES` almacena datos de precios de productos de una cadena entre fechas específicas, incluyendo detalles como identificadores de producto y tarifa, prioridades y monedas.

### Requisitos del Servicio

- **Entradas**:
    - Fecha de aplicación
    - Identificador de producto
    - Identificador de cadena

- **Salidas**:
    - Identificador de producto
    - Identificador de cadena
    - Tarifa aplicable
    - Fechas de aplicación del precio
    - Precio final

- **Base de Datos**:
    - Utilizar una base de datos en memoria (H2).
    - Inicialización con datos de ejemplo proporcionados.

### Tabla `PRICES`

**Descripción**:
La tabla `PRICES` captura los precios finales (PVP) y las tarifas aplicadas a productos de una cadena en periodos específicos, facilitando la gestión de promociones y cambios temporales en los precios.

**Estructura de la tabla**:
- **BRAND_ID**: Identifica la cadena del grupo (e.g., 1 = ZARA).
- **START_DATE**: Inicio de la validez del precio.
- **END_DATE**: Fin de la validez del precio.
- **PRICE_LIST**: Identificador de la tarifa de precios.
- **PRODUCT_ID**: Código del producto.
- **PRIORITY**: Prioridad en la aplicación de precios para solucionar coincidencias.
- **PRICE**: Precio de venta al público.
- **CURR**: Código ISO de la moneda.

**Ejemplo de datos**:
| PRICE_LIST | BRAND_ID | START_DATE   | END_DATE     | PRODUCT_ID | PRIORITY | PRICE | CURR |
|------------|----------|--------------|--------------|------------|----------|-------|------|
| 1          | 1        | 2020-06-14   | 2020-12-31   | 35455      | 0        | 35.50 | EUR  |
| 2          | 1        | 2020-06-14   | 2020-06-14   | 35455      | 1        | 25.45 | EUR  |
| 3          | 1        | 2020-06-15   | 2020-06-15   | 35455      | 1        | 30.50 | EUR  |
| 4          | 1        | 2020-06-15   | 2020-12-31   | 35455      | 1        | 38.95 | EUR  |

### Desarrollo de Tests para el Endpoint REST

- **Test 1**: Consulta a las 10:00 del 14 de junio para el producto 35455 de ZARA.
- **Test 2**: Consulta a las 16:00 del 14 de junio para el producto 35455 de ZARA.
- **Test 3**: Consulta a las 21:00 del 14 de junio para el producto 35455 de ZARA.
- **Test 4**: Consulta a las 10:00 del 15 de junio para el producto 35455 de ZARA.
- **Test 5**: Consulta a las 21:00 del 16 de junio para el producto 35455 de ZARA.

---

## Diseño y Arquitectura

### Bounded Context: `pricing`
En el contexto delimitado `pricing`, gestionamos todo lo relacionado con la fijación de precios en nuestra aplicación.

#### Aggregate: `products`

##### Capa de Dominio:
- **Aggregate Root**: `ProductPrice` actúa como el núcleo central donde se encapsula toda la lógica y el comportamiento esencial del dominio.
- **Value Objects**: Utilizados para evitar el uso de tipos primitivos y proporcionar una agrupación semántica que mejora la claridad y consistencia del dominio. Incluyen `ProductPriceId`, `ProductCode`, `BrandCode`, `RangeDateTime`, `Price`, y `Priority`, cada uno encapsulando comportamientos específicos y validaciones.
- **Interfaces**: Definiciones abstractas como `ProductPriceRepository`, que establecen el contrato para las operaciones de persistencia, asegurando así una abstracción adecuada y la separación de la lógica de acceso a datos.

##### Capa de Aplicación:
- **Casos de Uso**: Implementaciones específicas de la lógica de negocio, como `FindProductPriceFromDate`, diseñadas para manejar las solicitudes entrantes y coordinar las respuestas desde el dominio, asegurando que el flujo de información sea coherente y esté bien definido.

##### Capa de Infraestructura:
- **Controladores**: `GetProductPrice` sirve como ejemplo de cómo los controladores actúan como el punto de entrada para las solicitudes REST, limitándose a invocar los casos de uso correspondientes con una mínima lógica propia para mantener una clara separación de preocupaciones.
- **Persistencia**: Implementaciones de `ProductPriceRepository` tales como `H2ProductPriceRepository` para las 
  pruebas e2e y `InMemoryProductPriceRepository` para las pruebas unitarias de nuestro caso de uso. Esto se ha hecho así para demostrar la flexibilidad y el desacoplamiento del dominio frente a la infraestructura técnica.

## Técnicas
### TDD Outside-in y Honourable Retreat

En este proyecto, hemos buscado seguir un enfoque de TDD Outside-in para que la prueba de aceptación final me fuera 
guiando a la implementación completa. Esto lo he combinado con una técnica de refactoring llamada Honourable Retreat. Buscando realizar commits de manera frecuenta para poder volver al último commit en caso de necesitarlo. Facilitando el no tener que debuggear.
- **Prueba de Aceptación Final**: Utilizamos esta prueba como un faro, orientando todas las etapas del desarrollo y asegurando que la funcionalidad final se alinee con las expectativas del usuario y los objetivos del proyecto.

- **Commits Frecuentes**: Esta práctica nos permite realizar cambios incrementales y seguros. En caso de enfrentarnos a bloqueos significativos o errores complejos, los commits frecuentes facilitan la reversión a un estado anterior del código que se sabe que es estable y funcional. Al volver al último commit en verde cuando es necesario, evitamos largas sesiones de depuración.

## Caso de Uso Implementado y Diseño de URL
### Endpoint para Consultar Precios

Nuestro sistema implementa un endpoint REST que permite consultar el precio de un producto en una fecha específica. Este servicio es esencial para usuarios que necesitan conocer el precio aplicable en un momento determinado, típicamente en un contexto de comercio electrónico.
### Justificación del Diseño de URL:

- **Organización Lógica**: La decisión de organizar la URL comenzando con la marca (`brandCode`) y luego el producto (`productCode`) refleja la jerarquía natural en un entorno de comercio electrónico, donde los productos se categorizan por marcas.

- **Identificadores en la URL**: Tanto la marca como el producto son identificadores clave que definen el contexto de la consulta, por lo que incluirlos en la URL facilita un acceso directo y comprensible al recurso deseado.

- **Fecha como Parámetro de Consulta**: La fecha, siendo una variable más susceptible a cambios y consultas en diferentes momentos, se maneja mejor como un parámetro de consulta (`@RequestParam`). Esto permite flexibilidad para que los usuarios especifiquen diferentes momentos sin alterar la estructura básica de la URL.
## Levantar el servidor
### Docker:
```
docker compose up
```
### Maven:
```
./mvnw spring-boot:run
```
