#RegresionBooker_Delete
Feature: Realizar pruebas al metodo Delete de la API RESTFUL - BOOKER

  Rule: Cuando se realicen peticiones de borrado al servidor el metodo Delete y un token valido se debería permitir eliminar registros sin inconvenientes

    Background: Ingreso a la API restful Booker
      Given Ingreso a la API restful Booker para realizar peticiones Delete https://restful-booker.herokuapp.com
      When Envio una peticion al endpoint /booking para crear un registro de insumo para eliminarlo pl_crearBooking
      Then Valido que se cree correctamente el insumo y valido el codigo de estatus 200 OK


    #Eliminar registro en API#
    @happypath
    Scenario: Verificar que se permita eliminar registros del servicio con un token valido y un id valido
      When Envio una solicitud para borrar un registro al endpoint /booking/
      Then Valido que se borre correctamente con el codigo de estatus 201 Created

    @unHappypath
    Scenario: Verificar que al realizar una solicitud Delete al servidor con un token valido y un id Invalido o no existente se muestre un mensaje de validacion
      When Envio una peticion delete con un token valido y con un id invalido al endpoint /booking/74566
      Then Valido que se muestre el codigo de estatus 405 Method Not Allowed

    @unHappypath
    Scenario: Verificar que no se permita solicitar eliminar un registro cuando no se tiene el token correcto
      When Envio una solicitud delete con un token incorrecto y al endpoint /booking/
      Then Valido que no se permita eliminar sin token con el codigo de estatus 403 Forbidden

