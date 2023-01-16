RegresionBooker_PUT-PATCH
Feature: Realizar pruebas a los metodos PUT-PATCH de la API RESTFUL - BOOKER

  Rule: Cuando se realicen peticiones de actualización al servidor con los metodos PUT-PATCH y un token valido se debería permitir actualizar sin inconvenientes

    Background: Ingreso a la API restful Booker
      Given Que puedo acceder al servicio con la URI https://restful-booker.herokuapp.com

    #Obtener datos del API#
    @happypath
    Scenario: Verificar se actualice correctamente la información cuando se envie una petición de actualizacion PUT con un payload correcto
      When Envio una peticion PUT con Payload_UpdCorrecto al endpoint /booking/:id
      Then valido que se actualice correctamente la informacion y el codigo de estatus 200 OK