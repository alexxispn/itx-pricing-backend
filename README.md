# Inditex Caso Práctico

## _Endpoint de consulta para base de datos de precios_

![Java version](https://img.shields.io/badge/JDK-17%20preview-ED8B00?logo=openjdk&logoColor=white&style=for-the-badge)

# Índice

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

Este proyecto implica el desarrollo de una aplicación o servicio utilizando SpringBoot para proporcionar un endpoint
REST que permita consultar precios. La tabla `PRICES` almacena datos de precios de productos de una cadena entre fechas
específicas, incluyendo detalles como identificadores de producto y tarifa, prioridades y monedas.

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
La tabla `PRICES` captura los precios finales (PVP) y las tarifas aplicadas a productos de una cadena en periodos
específicos, facilitando la gestión de promociones y cambios temporales en los precios.

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
| PRICE_LIST | BRAND_ID | START_DATE | END_DATE | PRODUCT_ID | PRIORITY | PRICE | CURR |
|------------|----------|--------------|--------------|------------|----------|-------|------|
| 1 | 1 | 2020-06-14 | 2020-12-31 | 35455 | 0 | 35.50 | EUR |
| 2 | 1 | 2020-06-14 | 2020-06-14 | 35455 | 1 | 25.45 | EUR |
| 3 | 1 | 2020-06-15 | 2020-06-15 | 35455 | 1 | 30.50 | EUR |
| 4 | 1 | 2020-06-15 | 2020-12-31 | 35455 | 1 | 38.95 | EUR |

### Desarrollo de Tests para el Endpoint REST

- **Test 1**: Consulta a las 10:00 del 14 de junio para el producto 35455 de ZARA.
- **Test 2**: Consulta a las 16:00 del 14 de junio para el producto 35455 de ZARA.
- **Test 3**: Consulta a las 21:00 del 14 de junio para el producto 35455 de ZARA.
- **Test 4**: Consulta a las 10:00 del 15 de junio para el producto 35455 de ZARA.
- **Test 5**: Consulta a las 21:00 del 16 de junio para el producto 35455 de ZARA.

---

## Diseño y Arquitectura

### Bounded Context: `pricing`

En `pricing` gestionamos todo lo relacionado con la fijación de precios en nuestra aplicación.

#### Aggregate: `products`

##### Capa de Dominio:

- **Aggregate Root**: `ProductPrice` actúa como la raíz del agregado, encapsulando la lógica de negocio y sirviendo como
  punto de entrada para las operaciones relacionadas con los precios de los productos.
- **Value Objects**: Utilizados para evitar el uso de tipos primitivos y proporcionar una agrupación semántica de datos.
  Incluyen `ProductPriceId`, `ProductCode`, `BrandCode`, `RangeDateTime`, `Price`, y `Priority`, cada uno encapsulando
  comportamientos específicos y posibles validaciones.
- **Interfaces**: Definiciones abstractas como `ProductPriceRepository`, que establecen contratos para la persistencia
  de
  precios de productos. Esto permite que las implementaciones concretas se inyecten en el caso de uso sin acoplar el
  dominio a una tecnología específica.

##### Capa de Aplicación:

- **Casos de Uso**: `FindProductPriceFromDate` encapsula la lógica de negocio para recuperar el precio de un producto en
  una fecha específica. Al ser un caso de uso, se enfoca en la lógica de negocio y delega la persistencia a través de
  `ProductPriceRepository`.

##### Capa de Infraestructura:

- **Controladores**: `GetProductPriceController` actúa como el punto de entrada para las solicitudes REST, limitándose a
  invocar el caso de uso correspondiente con una mínima lógica propia para mantener una clara separación de
  preocupaciones.
- **Persistencia**: Implementaciones de `ProductPriceRepository` tales como `H2ProductPriceRepository` para las
  pruebas e2e y `InMemoryProductPriceRepository` para las pruebas unitarias de nuestro caso de uso. Esto se ha hecho así
  para demostrar la flexibilidad de la arquitectura y la facilidad de intercambiar implementaciones de repositorios.

## Técnicas

### TDD Outside-in y Honourable Retreat

En este proyecto, he seguido un enfoque de TDD Outside-in, comenzando con la prueba de aceptación final y trabajando
hacia abajo para implementar las pruebas unitarias y las clases de dominio necesarias. Este enfoque nos permite
desarrollar de manera incremental y centrarnos en la funcionalidad requerida, evitando la implementación de código
innecesario. Además, he aplicado la técnica de Honourable Retreat, que consiste en realizar commits de manera
frecuente para poder volver al último commit en caso de necesitarlo.

- **Prueba de Aceptación Final**: Utilizamos esta prueba como un faro, orientando todas las etapas del desarrollo y
  asegurando que el sistema cumpla con los requisitos del negocio. Al comenzar con la prueba de aceptación, podemos
  definir claramente el comportamiento esperado y trabajar hacia abajo para implementar las pruebas unitarias y las
  clases de dominio necesarias, evitando la implementación de código innecesario.

- **Commits Frecuentes**: Esta práctica nos permite realizar cambios incrementales y seguros. En caso de enfrentarnos a
  bloqueos significativos o errores complejos, los commits frecuentes facilitan la reversión a un estado anterior del
  código que se sabe que es estable y funcional. Al volver al último commit en verde cuando es necesario, evitamos
  largas sesiones de depuración.

## Caso de Uso Implementado y Diseño de URL

### Endpoint para Consultar Precios

Nuestro sistema implementa un endpoint REST que permite consultar el precio de un producto en una fecha específica.
Este servicio permite que los usuarios puedan conocer el precio aplicable en un momento determinado. Típico de un
entorno de comercio electrónico, donde los precios pueden variar según la fecha y la disponibilidad.

### Justificación del Diseño de URL:

#### Get: /prices/{brandCode}/{productCode}?date={date}

- **Organización Lógica**: La decisión de organizar la URL comenzando con la marca (`brandCode`) y luego el
  producto (`productCode`) refleja la jerarquía natural en un entorno de comercio electrónico, donde los productos se
  categorizan por marcas.

- **Identificadores en la URL**: Tanto la marca como el producto son identificadores clave que definen el contexto de la
  consulta, por lo que incluirlos en la URL facilita un acceso directo y comprensible al recurso deseado.

- **Fecha como Parámetro de Consulta**: La fecha, siendo una variable más susceptible a cambios y consultas en
  diferentes momentos, se maneja mejor como un parámetro de consulta (`@RequestParam`). Esto permite flexibilidad para
  que los usuarios especifiquen diferentes momentos sin alterar la estructura básica de la URL.

## Levantar el servidor

### Docker:

```
docker compose up
```

### Maven:

```
./mvnw spring-boot:run
```

Podemos conectarnos a la base de datos H2 en `http://localhost:8080/h2-console` con las siguientes credenciales:

- **JDBC URL**: `jdbc:h2:mem:pricing`
- **User Name**: `sa`
- **Password**: `password`
