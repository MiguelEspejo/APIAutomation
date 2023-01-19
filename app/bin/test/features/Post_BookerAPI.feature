@RegresionBooker_POST
Feature: Realizar pruebas a los metodos POST de la API RESTFUL - BOOKER

  Rule: Cuando se realicen peticiones POST al sevidor deberíamos obtener la información sin inconvenientes y a su vez poder tener una integración de la información.

    Background: Ingreso a la API restful Booker peticiones POST
      Given Que puedo acceder al servicio con la URI https://restful-booker.herokuapp.com para probar el metodo POST

    #Obtener Token#
    @happypath
    Scenario: Verificar que al enviar las credenciales correctas se obtenga un token que permita actualizar o eliminar registros en la API Booker
      When Envio una peticion POST con el payloadAuth correcto al endpoint /auth
      Then valido que exista el token en la respuesta y el codigo de estatus sea 200 OK

    @unHappyPath
    Scenario: Verificar que cuando se envie usuario incorrecto y contraseña correcta se muestre una validación indicando credenciales invalidas y no se obtenga el token
      When Envio una petición POST con el payloadAuth_usrInv incorrecto al endpoint /auth
      Then valido el siguiente mensaje de validación "Bad credentials" en la respuesta, cuando el usuario sea incorrecto y el codigo de estatus sea 200 OK

    @unHappyPath
    Scenario: Verificar que cuando se envie usuario correcto y contraseña incorrecta se muestre una validación indicando credenciales invalidas y no se obtenga el token
      When Envio una petición POST con el payloadAuth_passInv incorrecto al endpoint /auth
      Then valido el siguiente mensaje de validación "Bad credentials" en la respuesta, cuando la contraseña sea incorrecta y el codigo de estatus sea 200 OK

    @unHappyPath
    Scenario: Verificar que cuando se envie usuario incorrecto y contraseña incorrecta se muestre una validación indicando credenciales invalidas y no se obtenga el token
      When Envio una petición POST con el payloadAuth_usr_passInv incorrecto al endpoint /auth
      Then valido el siguiente mensaje de validación "Bad credentials" en la respuesta, cuando se envie usuario y contraseña incorrectos y el codigo de estatus sea 200 OK

    @unHappyPath
    Scenario: Verificar que cuando se envie usuario vacio y contraseña correcta se muestre una validación indicando credenciales invalidas y no se obtenga el token
      When Envio una petición POST con el payloadAuth_usrVacio incorrecto al endpoint /auth
      Then valido el siguiente mensaje de validación "Bad credentials" en la respuesta, cuando se envie usuario vacio y el codigo de estatus sea 200 OK

    @unHappyPath
    Scenario: Verificar que cuando se envie usuario correcto y contraseña vacia se muestre una validación indicando credenciales invalidas y no se obtenga el token
      When Envio una petición POST con el payloadAuth_passVacio incorrecto al endpoint /auth
      Then valido el siguiente mensaje de validación "Bad credentials" en la respuesta, cuando se envie contraseña vacia y el codigo de estatus sea 200 OK

    @unHappyPath
    Scenario: Verificar que cuando se envie usuario vacio y contraseña vacia se muestre una validación indicando credenciales invalidas y no se obtenga el token
      When Envio una petición POST con el payloadAuth_passVacio incorrecto al endpoint /auth
      Then valido el siguiente mensaje de validación "Bad credentials" en la respuesta, cuando se envie usuario y contraseña vacios y el codigo de estatus sea 200 OK

    @unHappyPath
    Scenario: Verificar que cuando se envie la petición con un formato invalido no se obtenga token
      When Envio una petición POST con el payloadAuth_Inv invalido al endpoint /auth
      Then valido el siguiente mensaje de validación "Bad credentials" en la respuesta, cuando la petición sea invalida y el codigo de estatus sea 200 OK

      #Crear Registro#
    @happypath
    Scenario: Verificar que al enviar la petición de manera correcta se cree un nuevo registro en la API Booker
      When Envio una peticion POST con el payload_crearBook correcto al endpoint /booking
      Then valido que se creeo correctamente el registro y el codigo de estatus sea 200 OK

    @happypath
    Scenario: Verificar que al intentar crear un registro nuevo con un formato de fecha incorrecto se muestre un mensaje de validación
      When Envio una peticion POST con el payload_crearFechaInv correcto al endpoint /booking
      Then valido que no se cree el registro y el codigo de estatus sea 500 Internal Server Error

    @unHappypath
    Scenario: Verificar que al enviar la petición de manera incorrecta con un dato faltante no se cree ningún registro en la API Booker
      When Envio una peticion POST con el payload_crearInv invalido al endpoint /booking
      Then valido que el codigo de estatus sea 500 Internal server Errror

    @unHappypath
    Scenario: Verificar que no se permita crear un registro nuevo al enviar una petición con el campo totalprice vacio
      When Envio una peticion POST con el payload_crearTotPriceVacio invalido al endpoint /booking
      Then valido que el codigo de estatus sea 400 bad request

    @unHappypath
    Scenario: Verificar que se muestre el campo totalprice correctamente cuando se envie una petición con el campo totalprice con valor decimal
      When Envio una peticion POST con el payload_crearTotPriceDec al endpoint /booking
      Then valido que en la respuesta se muestre el campo totalprice 11.99 y el codigo de estado 200 OK


      #validación de ping al servicio#
    @happypath
    Scenario: Verificar que el servicio se encuentre disponible
      When Envio una peticion POST al endpoint /ping
      Then valido el codigo de estado 201 Created


