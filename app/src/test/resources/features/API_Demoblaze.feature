@RegressionAPIDemoBlaze
Feature: Revisar la funcionalidad del servicio  api demoblaze

  Rule: Solicitudes POST al servicio de la tienda demoblaze

    Background: Navego a la tienda demo blaze
      Given Que puedo acceder al servicio con la URI para solicitar peticiones

      ##ValidacionesAPI
    @Regresion @Smoke
    Scenario: Verificar que se permita crear un usuario mediante el servicio de DemoBlaze
      When Envio una solicitud para registrar usuario pl_SignUp al endpoint /signup
      Then Valido que se registre correctamente el usuario con el codigo de estatus sea 200 OK

    @Regresion @Smoke
    Scenario: Verificar que se permita obtener un token enviando una peticion con credenciales correctas a el servicio de DemoBlaze
      When Envio una solicitud para obtener token de usuario pl_SignIn al endpoint /login
      Then Valido que se genere un Auth_token: y el codigo de estatus sea 200 OK

    @Regresion
    Scenario: Verificar que no se permita obtener token con un usuario que no este registrado
      When Envio una solicitud para obtener token con un usuario invalido pl_SignIn_usrInv al endpoint /login
      Then Valido que no se genere token y muestre el siguiente mensaje User does not exist. y el codigo de estatus sea 200 OK

    @Regresion
    Scenario: Verificar que no se permita obtener token con un usuario con una contraseña incorrecta
      When Envio una solicitud para obtener token con contrasena incorrecta pl_SignIn_passInv al endpoint /login
      Then Valido que no se genere token y cuando se envie contrasena invalida se muestre el siguiente mensaje Wrong password. y el codigo de estatus sea 200 OK