RegresionBooker_PUT-PATCH
Feature: Realizar pruebas a los metodos PUT-PATCH de la API RESTFUL - BOOKER

  Rule: Cuando se realicen peticiones de actualización al servidor con los metodos PUT-PATCH y un token valido se debería permitir actualizar sin inconvenientes

    Background: Ingreso a la API restful Booker
      Given Que puedo acceder al servicio con la URI https://restful-booker.herokuapp.com

    #Actualiza datos de un registro en API#
    @happypath
    Scenario: Verificar que se actualice correctamente la información al enviar la petición de actualización con token correcto
      When Envio una peticion PUT con Payload_UpdCorrecto al endpoint /booking/:id
      Then valido que se actualice correctamente la informacion y el codigo de estatus 200 OK

    @unHappypath
    Scenario: Verificar que no se actualice la información cuando se envíe un payload con formato de fechas invalidas
      When Envio una peticion PUT con Payload_UpdFechaInv al endpoint /booking/:id
      Then valido que se muestre la validación correspondiente y el codigo de estatus 500 Internal Server Error

    @unHappypath
    Scenario: Verificar que no se permita actualizar información en el servicio cuando se mande la petición sin token o con un token incorrecto
      When Envio una peticion PUT sin token con Payload_updNoToken al endpoint /booking/:id
      Then valido que se muestre el siguiente mensaje Forbidden y el codigo de estatus 402 Forbidden

      #PATCH#
    @happypath
    Scenario: Verificar que al enviar solo el nombre y apellido de la persona en la petición con token correcto se actualice sin inconvenientes
      When Envio una peticion PATCH con solo el nombre y apellido de la persona con Payload_patchCorrecto al endpoint /booking/:id
      Then valido que se actualice solo los campos enviados en la petición y el codigo de estatus 200 OK

    @unHappypath
    Scenario: Verificar que no se actualice la información al enviar solo el campo fecha de manera incorrecta
      When Envio una peticion PATCH con solo el campo fecha Payload_UpdFechaInv al endpoint /booking/:id
      Then valido que se muestre la validación correspondiente y el codigo de estatus 500 Internal Server Error

    @unHappypath
    Scenario: Verificar que al enviar una petición PATCH no se permita actualizar información en el servicio cuando se mande la petición sin token o con un token incorrecto
      When Envio una peticion PUT sin token con Payload_updNoToken al endpoint /booking/:id
      Then valido que se muestre el siguiente mensaje Forbidden y el codigo de estatus 402 Forbidden

