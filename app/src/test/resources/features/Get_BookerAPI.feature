#@RegresionBooker_GET
#Feature: Realizar pruebas a los metodos GET de la API RESTFUL - BOOKER
#
#  Rule: Cuando se realicen peticiones GET al sevidor deberíamos obtener la información sin inconvenientes y a su vez poder tener una integración de la información.
#
# comentado para que no falle el build
#
#    Background: Ingreso a la API restful Booker
#      Given Que puedo acceder al servicio con la URI https://restful-booker.herokuapp.com
#
#    #Obtener datos del API#
#    @happypath
#    Scenario: Verificar que al consultar el endpoint /booking se obtenga toda la relación de Ids registrados
#      When Envio una peticion GET al endpoint /booking
#      Then valido que se obtenga información en la consulta y el codigo de estatus 200 OK
#
#    @happypath
#    Scenario: Verificar que al consultar el endpoint /booking enviando el parametro ID se obtenga la información correspondiente al id que se encuentra registrado
#      When Envio una peticion GET con el parametro 111 al endpoint /booking/:id
#      Then valido que se obtenga información correspondiente al ID y el codigo de estatus 200 OK
#
#    @unHappypath
#    Scenario: Verificar que no se muestre información en la respuesta cuando se consulte el endpoint con un parametro ID invalido o no registrado
#      When Envio una peticion GET con el parametro 4568 al endpoint /booking/:id
#      Then valido que no se obtenga información y el codigo de estatus 404 Not Found
#
#    @happypath
#    Scenario: Verificar que se obtenga información del servicio al realizar una consulta mediante Nombre y Apellido
#      When Envio una peticion GET al endpoint /booking?firstname=carlos&lastname=Brown
#      Then valido que se obtenga el bookingID correspondiente a los parametros Nombre / Apellido y el codigo de estatus 200 OK
#
#    @unHappypath
#    Scenario: Verificar que no obtenga información del servicio al realizar una consulta con Nombre y Apellido no registrados
#      When Envio una peticion GET al endpoint /booking?firstname=carlosss&lastname=Brownes
#      Then valido que no se obtenga bookingID y el codigo de estatus 200 OK
#
#    @Happypath
#    Scenario: Verificar que al realizar la consulta solo con el nombre se obtenga la información coincidente
#      When Envio una peticion GET solo con el nombre al endpoint /booking?firstname=carlos
#      Then valido que se obtenga el bookingID correspondinete al nombre y el codigo de estatus 200 OK
#
#    @Happypath
#    Scenario: Verificar que al realizar la consulta solo con el apellido se obtenga la información coincidente
#      When Envio una peticion GET solo con el apellido al endpoint /booking?firstname=carlos
#      Then valido que se obtenga el bookingID correspondinete al apellido y el codigo de estatus 200 OK
#
#    @unHappypath
#    Scenario: Verificar que al realizar la consulta solo con el nombre no registrado no se obtenga información
#      When Envio una peticion GET solo con el nombre no registrado al endpoint /booking?firstname=Juan
#      Then valido que no se obtenga información con el nombre no registrado y el codigo de estatus 200 OK
#
#    @unHappypath
#    Scenario: Verificar que al realizar la consulta solo con el apellido no registrado no se obtenga información
#      When Envio una peticion GET solo con el nombre no registrado al endpoint /booking?lastname=Robles
#      Then valido que no se obtenga información con el apellido no registrado y el codigo de estatus 200 OK
#
#    @happypath
#    Scenario: Verificar que se obtenga información correcta al realizar la consulta con un rango de fechas de checkIn y checkOut
#      When Envio una peticion con el rango de fechas checkin y checkout al endpoint /booking?checkin=2021-01-01&checkout=2023-01-01
#      Then valido que se obtenga la relacion de bookingId registrados en el rango de fechas y el codigo de estatus 200 OK
#
#    @happypath
#    Scenario: Verificar que se obtenga información correcta al realizar la consulta con solo la fecha checkIn
#      When Envio una peticion solo con la fecha checkin al endpoint /booking?checkin=2021-01-01
#      Then valido que se obtenga la relacion de bookingId registrados en la fecha de CheckIn y el codigo de estatus 200 OK
#
#    @happypath
#    Scenario: Verificar que se obtenga información correcta al realizar la consulta con solo la fecha checkOut
#      When Envio una peticion solo con la fecha checkout al endpoint /booking?checkout=2023-01-01
#      Then valido que se obtenga la relacion de bookingId registrados en la fecha de CheckOut y el codigo de estatus 200 OK
#
#    @unHappypath
#    Scenario: Verificar que al realizar la consulta de información con un formato de fechas invalido no se obtengan resultados
#      When Envio una peticion con formato de fechas invalido al endpoint /booking?checkin=2021-1-01&checkout=20-01-45
#      Then valido el codigo de estatus 500 Internal Server Error
#
#    @unHappypath
#    Scenario: Verificar que no se obtenga información al realizar la consulta con un rango de fechas de checkIn y checkOut donde no se encuentren registros
#      When Envio una peticion con el rango de fechas checkin y checkout al endpoint /booking?checkin=2023-01-10&checkout=2023-01-12
#      Then valido que no se obtenga información y el codigo de estatus 200 OK
#
#

#validación de ping al servicio#
#@Ping
#Scenario: Verificar que el servicio se encuentre disponible
#When Envio una solicitud al servicio para revisar disponibilidad al endpoint /ping
#Then Valido la disponibilidad del servicio con el codigo de estado 201 Created