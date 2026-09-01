# Proyecto de Automatización de Pruebas

Proyecto desarrollado para automatizar pruebas Web y API utilizando Java.

## Tecnologías utilizadas

- Java 17
- Maven
- Selenium WebDriver
- JUnit 5
- REST Assured
- IntelliJ IDEA
- Google Chrome

---

# Pruebas Web - SauceDemo

Página utilizada:

https://www.saucedemo.com/

Se automatizaron 5 escenarios utilizando Selenium WebDriver,
JUnit y Page Object Model.

## Casos automatizados

### WEB-01
Agregar producto al carrito.

### WEB-02
Eliminar producto del carrito.

### WEB-03
Ordenar productos de menor a mayor precio.

### WEB-04
Completar correctamente una compra.

### WEB-05
Validar mensaje de error al continuar checkout sin completar datos obligatorios.

Nota:
El Login se utiliza únicamente como precondición para acceder al sistema y no como caso de prueba.

---

# Pruebas API - Restful Booker

API utilizada:

https://restful-booker.herokuapp.com/

Se automatizaron 5 escenarios utilizando REST Assured y JUnit.

## Casos automatizados

### API-01
GET - Obtener lista de reservas.

### API-02
POST - Crear una reserva correctamente.

### API-03
POST + GET - Crear una reserva y consultarla mediante ID.

### API-04
GET - Consultar una reserva inexistente.

### API-05
POST + GET - Crear una reserva y buscarla por nombre y apellido.

---

# Conceptos aplicados

## Selenium

- WebDriver
- Selectors
- Assertions
- Page Object Model
- JUnit
- BeforeEach
- AfterEach

## REST Assured

- REST Calls
- GET
- POST
- Validación de códigos HTTP
- Validación de respuestas JSON
- Extracción de datos
- Assertions

---

# Ejecución

Ejecutar todas las pruebas mediante Maven:

---

# Resultado final

Pruebas ejecutadas:

- 5 pruebas Web
- 5 pruebas API

Total:

10 pruebas automatizadas

Resultado:

BUILD SUCCESS