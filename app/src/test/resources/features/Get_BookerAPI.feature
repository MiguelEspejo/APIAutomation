@RegresionBooker_GET
Feature: Realizar pruebas a los metodos GET de la API RESTFUL - BOOKER

  Rule: Cuando se realicen peticiones GET al sevidor deberíamos obtener la información sin inconvenientes y a su vez poder tener una integración de la información.

    Background: Ingreso a la API restful Booker
      Given Ingreso a la API restful Booker para solicitar peticiones GET https://restful-booker.herokuapp.com

    #Obtener datos del API#
    @Regresion @Smoke
    Scenario: Verificar que al consultar el endpoint se obtenga toda la relación de Ids registrados
      When Envio una solicitud para obtener todos los registros del servicio al endpoint /booking
      Then Valido que se obtenga informacion en la consulta y el codigo de estatus 200 OK

      #a veces falla el test por el reinicio del servicio
    @Regresion @Smoke
    Scenario: Verificar que se obtenga información al realizar una consulta por id al servicio
      When Envio una solicitud para obtener la informacion del folio 582 al endpoint /booking/
      Then Valido que se obtenga informacion correspondiente al ID con el nombre John y apellido Smith y el codigo de estatus 200 OK

    @Regresion
    Scenario: Verificar que no se muestre información en la respuesta cuando se consulte el servicio con un parametro ID invalido o no registrado
      When Envio una solicitud con un folio 456898 no registrado al endpoint /booking/
      Then Valido que no se obtenga informacion del ID con el codigo de estatus 404 Not Found

      #a veces falla el test por el reinicio del servicio
    @Regresion @Smoke
    Scenario: Verificar que se obtenga información del servicio al realizar una consulta mediante Nombre y Apellido
      When Envio una solicitud de consulta para obtener la informacion con el nombre John y apellido Smith /booking?firstname=John&lastname=Smith
      Then Valido que se obtenga la relacion de Ids que contengan el nombre y apellido y el codigo de estatus 200 OK

    @Regresion
    Scenario: Verificar que no obtenga información del servicio al realizar una consulta con Nombre y Apellido no registrados
      When Envio una solicitud de consulta al servicio con nombre Roberto Carlos y apellido Soto al endpoint /booking?firstname=Roberto Carlos&lastname=Soto
      Then Valido que no se obtenga relacion de ids con ese nombre y el codigo de estatus 200 OK


    @Regresion
    Scenario: Verificar que al realizar la consulta solo con el nombre se obtenga la información coincidente
      When Envio una solicitud de consulta solo con el nombre John al endpoint /booking?firstname=John
      Then Valido que se obtenga la relacion de ids correspondiente al nombre y el codigo de estatus 200 OK

    @Regresion
    Scenario: Verificar que al realizar la consulta solo con el apellido se obtenga la información coincidente
      When Envio una solicitud de consulta solo con el Apellido Smith al endpoint /booking?lastname=Smith
      Then Valido que se obtenga la relacion de ids correspondiente al Apellido y el codigo de estatus 200 OK

    @Regresion @Smoke
    Scenario: Verificar que se obtenga información al realizar la consulta con un rango de fechas de checkIn y checkOut
      When Envio una solicitud de consulta con un rango de checkin=2022-01-10&checkout=2023-01-12 al endpoint /booking?checkin=2022-01-10&checkout=2023-01-12
      Then Valido que se obtenga la relacion de ids registrados en el rango de fechas y el codigo de estatus 200 OK

     @Regresion
    Scenario: Verificar que se obtenga información correcta al realizar la consulta con solo la fecha checkIn
      When Envio una solicitud solo con la fecha checkin 2022-01-10 al endpoint /booking?checkin=2022-01-10
      Then valido que se obtenga la relacion de bookingId registrados en la fecha de CheckIn y el codigo de estatus 200 OK

    @Regresion
    Scenario: Verificar que se obtenga información correcta al realizar la consulta con solo la fecha checkOut
      When Envio una solicitud solo con la fecha checkout 2023-01-12 al endpoint /booking?checkout=2023-01-12
      Then Valido que se obtenga la relacion de bookingId registrados en la fecha de CheckOut y el codigo de estatus 200 OK


    @Ping @Regresion @Smoke
    Scenario: Verificar que el servicio se encuentre disponible
      When Envio una solicitud al servicio para revisar disponibilidad al endpoint /ping
      Then Valido la disponibilidad del servicio con el codigo de estado 201 Created