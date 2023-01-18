@RegresionBooker_PUT-PATCH
Feature: Realizar pruebas a los metodos PUT-PATCH de la API RESTFUL - BOOKER

  Rule: Cuando se realicen peticiones de actualización al servidor con los metodos PUT-PATCH y un token valido se debería permitir actualizar sin inconvenientes

    Background: Ingreso a la API restful Booker
      Given Ingreso a la API restful Booker para realizar peticiones PUT-PATCH https://restful-booker.herokuapp.com
      When Envio una peticion al endpoint /booking para crear un registro de insumo para poder actualizar pl_crearBooking
      Then Valido que se cree correctamente registro y valido el codigo de estatus 200 OK

    #Actualiza datos de un registro en API#
    @Regresion @Smoke
    Scenario: Verificar que se actualice correctamente el Nombre, apellido y el precio total
      When Envio una solicitud para actualizar la informacion del Booking Pl_UpdCorrecto al endpoint /booking/
      Then valido que se actualice correctamente el nombre Carlos el apellido Martinez y el precio 45 y el codigo de estatus 200 OK

    @Regresion
    Scenario: Verificar que no se actualice la información cuando se envíe un payload con formato de fechas invalidas
      When Envio una solicitud para actualizar con fechas invalidas Pl_UpdFechaInv al endpoint /booking/
      Then Valido que no se ejecute la actualizacion con el codigo de estatus 500 Internal Server Error

    @Regresion @Smoke
    Scenario: Verificar que no se permita actualizar información en el servicio cuando se realice una solicitud de actualizaciónn sin contar con un token valido
      When Envio una solicitud de actualizacion sin contar con un token valido Pl_UpdCorrecto al endpoint /booking/
      Then valido el codigo de estatus 403 Forbidden al intentar realizar una actualizacion sin token

     #PATCH#
    @Regresion @Smoke
    Scenario: Verificar que al enviar solo el nombre y apellido de la persona en la petición con token correcto se actualice sin inconvenientes
      When Envio una solicitud de actualizacion con solo el nombre y apellido de la persona Pl_patchCorrecto al endpoint /booking/
      Then Valido que el nombre actualizado sea Juan y el apellido actualizado sea Perez y el codigo de estatus 200 OK

    @Regresion @Smoke
    Scenario: Verificar que no se permita solicitar una actualización de solo nombre y apellido sin contar con un token valido
      When Envio una solicitud de actualizacion de nombre y apellido sin token Pl_patchCorrecto al endpoint /booking/
      Then Valido que no se actualice la informacion con el codigo de estatus 403 Forbidden

