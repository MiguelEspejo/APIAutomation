# Framework de automatizacion API
### Descripción:
##### Este es un framework creado la automatización de pruebas al servicio RestFul-Booker.

###Documentación del servicio
###### La API cuenta con una pagina donde se muestra la documentación necesaría para poder realizar peticiones.
> URL Doc: https://restful-booker.herokuapp.com/apidoc/index.html

###Herramientas
1. Java Jdk 17.
2. Junit 5.7
3. Cucumber java 7.10.1
4. RestAssured 5.3.0
5. ExtentReports 1.9.2
6. Jenkins

###Requerido
1. JDK 17

###Instrucciones
1. Clonar el repositorio: *https://github.com/MiguelEspejo/APIAutomation.git*
2. Ejecutar en la terminal el comando: `gradle clean`
3. Ejecutar en la terminal el comando: `gradle build`
4. Ejecutar en la terminal el comando para ejecutar el smoke test del servicio.  `gradle test -DcucumberOptions="--tags @Smoke" ` 

