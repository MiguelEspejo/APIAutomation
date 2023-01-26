
# Automatización API Opc1 DemoBlaze
###### Decripción:
- Pruebas de smoke al servicio https://api.demoblaze.com registro de usuario y inicio de sesión.

###Herramientas
1. Java Jdk 17.
2. RestAssured
3. Junit 5.7
4. Cucumber java 7.10.1
5. ExtentReports 1.9.2

###Requerido
JDK 17

###Instrucciones
1. Ejecutar en la terminal el comando: `gradle clean`
2. Ejecutar en la terminal el comando: `gradle build`
3. Ejecutar en la terminal el comando para ejecutar el smoke test del servicio.  `gradle test -DcucumberOptions="--tags @Smoke" ` 
