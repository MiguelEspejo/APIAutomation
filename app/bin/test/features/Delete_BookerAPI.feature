RegresionBooker_Delete
Feature: Realizar pruebas al metodo Delete de la API RESTFUL - BOOKER

  Rule: Cuando se realicen peticiones de borrado al servidor el metodo Delete y un token valido se debería permitir eliminar registros sin inconvenientes

    Background: Ingreso a la API restful Booker
      Given Que puedo acceder al servicio con la URI https://restful-booker.herokuapp.com
      #puedo generar el token aqui tmb#

    #Eliminar registro en API#
    @happypath
    Scenario: Verificar que al realizar una petición Delete al servidor con un token valido y un id valido se elimine correctamente el registro
      When Envio una peticion delete con un token valido y con un id valido al endpoint /booking/7046
      Then valido que se muestre el siguiente mensaje: Created y elimine la informacion del registro y el codigo de estatus 201 Created

    @unHappypath
    Scenario: Verificar que no se permita enviar una petición para eliminar un mismo registro dos o mas veces veces
      When Envio una peticion delete con un token valido y con el ID repetido al endpoint /booking/7046
      Then valido que se muestre el siguiente mensaje de validación: Method Not Allowed y el codigo de estatus 405 Method Not Allowed

    @unHappypath
    Scenario: Verificar que al realizar una petición Delete al servidor con un token valido y un id Invalido o no existente se muestre un mensaje de validacion
      When Envio una peticion delete con un token valido y con un id invalido al endpoint /booking/74566
      Then valido que se muestre el siguiente mensaje: Method Not Allowed y el codigo de estatus 405 Method Not Allowed

    @unHappypath
    Scenario: Verificar que al realizar una petición Delete al servidor con un token invalido o sin token y un id valido se muestre un mensaje de validación
      When Envio una peticion delete con un token invalido y con un id valido al endpoint /booking/4553
      Then valido que se muestre el siguiente mensaje: Forbidden y el codigo de estatus 403 Forbidden

