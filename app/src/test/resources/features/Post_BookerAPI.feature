@RegresionBooker_POST
Feature: Realizar pruebas a los metodos POST de la API RESTFUL - BOOKER

  Rule: Cuando se realicen peticiones POST al sevidor deberíamos obtener la información sin inconvenientes y a su vez poder tener una integración de la información.

    Background: Ingreso a la API restful Booker peticiones POST
      Given Que puedo acceder al servicio con la URI https://restful-booker.herokuapp.com para probar el metodo POST

    #Obtener Token#
    @happypath_espera
    Scenario: Verificar que al enviar las credenciales correctas se obtenga un token que permita actualizar o eliminar registros en la API Booker
      When Envio una solicitud con las credenciales correctas pl_Auth al endpoint /auth
      Then valido que se genere el token correctamente y el codigo de estatus sea 200 OK

    @happyPath_espera
    Scenario Outline: Verificar que cuando se envie usuario correcto y contraseña incorrecta se muestre una validación indicando credenciales invalidas y no se obtenga el token
      When Envio una solicitud con las credenciales incorrectas <pl_authInv> al endpoint /auth
      Then Valido el siguiente mensaje de validacion Bad credentials en la respuesta y el codigo de estatus sea 200 OK
  Examples:
  | pl_authInv        |
  | pl_Auth_passInv   |
  | pl_Auth_usrInv    |
  | pl_AuthCredInv    |
  | pl_AuthCredVacias |


# Crear Registro#
    @happypath_espera
    Scenario: Verificar que se permita crear bookings sin inconvenientes
      When Envio una peticion POST con el pl_crearBooking correcto al endpoint /booking
      Then Valido que se cree correctamente el Registro con el nombre: Carloss y el codigo de estatus sea 200 OK

    @happypath_espera
    Scenario: Verificar que al enviar un registro nuevo con un formato de fecha incorrecto se muestre un mensaje de validacion y no se registre el booking
      When Envio una peticion post con el payload pl_crearBookingFechaInv con fechas invalidas al endpoint /booking
      Then Valido el codigo de estatus sea 500 Internal Server Error


    @unHappypath_espera
    Scenario: Verificar que no se permita crear un registro de booking si falta algun dato en la peticion
      When Envio una peticion post con sin el campo totalprice pl_crearBookingDatoFaltante al endpoint /booking
      Then Valido que no se registre con el codigo de estatus 500 Internal Server Error

    @unHappypath_espera
    Scenario: Verificar que se muestre el campo totalprice correctamente cuando se envie una petición con el campo totalprice con valor decimal
      When Envio una peticion con el campo totalprice con decimal pl_crearTotPriceDec al endpoint /booking
      Then valido que en la respuesta se muestre el campo totalprice 11.99 y el codigo de estado 200 OK


