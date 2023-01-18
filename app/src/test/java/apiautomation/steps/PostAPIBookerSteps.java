package apiautomation.steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PostAPIBookerSteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    //background
    @Given("^Que puedo acceder al servicio con la URI (.+) para probar el metodo POST$")
    public void accesoAlservicio(String uri){
        request = given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .baseUri(uri)
                .contentType(ContentType.JSON);
    }
    //creación de token
    @When("^Envio una solicitud con las credenciales correctas (.+) al endpoint (.+)$")
    public void crearToken(String genTokenPayload, String endPoint){

        File reqBodyGenToken = new File("src/test/resources/payloads/POST/" + genTokenPayload + ".json");
        response = request
                .when()
                .header("Content-Type","application/json")
                .body(reqBodyGenToken)
                .post(endPoint)
                .prettyPeek();
    }
    @Then("^valido que se genere el (.+) correctamente y el codigo de estatus sea (\\d+) OK$")
    public void validarGenToken(String tokenExiste, int estatusCodeEsperado){
        json = response.then().statusCode(estatusCodeEsperado);
        String jsonResponseToken = response.jsonPath().getString("$");
        assertTrue("El token se creeo correctamente ", jsonResponseToken.contains(tokenExiste));
    }

    //generación de token contraseña incorrecta
    @When("^Envio una solicitud con las credenciales incorrectas (.+) al endpoint (.+)")
    public void crearTokenCredMal(String pl_AuthInv, String endPoint){
        File reqBodyGenTokenUsrInv = new File("src/test/resources/payloads/POST/" + pl_AuthInv + ".json");
        response = request
                .when()
                .header("Content-Type","application/json")
                .body(reqBodyGenTokenUsrInv)
                .post(endPoint)
                .prettyPeek();

    }
    @Then("^Valido el siguiente mensaje de validacion (.+) en la respuesta y el codigo de estatus sea (\\d+) OK$")
    public void validarGenTokenCredMal(String msjValidacion, int estatusCodeEsperado){
        json = response.then().statusCode(estatusCodeEsperado);
        String jsonResponseTokenUsrInv = response.jsonPath().getString("$");
        assertTrue("Se genero el token correctamente y no se mostro el mensaje de validacion ", jsonResponseTokenUsrInv.contains(msjValidacion));
    }

    //crear booking
    @When("^Envio una peticion POST con el (.+) correcto al endpoint (.+)$")
    public void crearBookingPost(String createBookingPayload, String endPoint){

        File reqBodyCreateBooking = new File("src/test/resources/payloads/POST/" + createBookingPayload + ".json");
        response = request
                .when()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .body(reqBodyCreateBooking)
                .post(endPoint)
                .prettyPeek();
    }
    @Then("^Valido que se cree correctamente el Registro con el nombre: (.+) y el codigo de estatus sea (\\d+) OK$")
    public void validarBookingCreado(String registroNomEsperado, int estatusCodeEsperado){
        json = response.then().statusCode(estatusCodeEsperado);
        String jsonResponsefirstName = response.jsonPath().getString("booking.firstname");
        assertTrue("El booking del usuario " +registroNomEsperado+ " no fue creado, no existe en la respuesta ", jsonResponsefirstName.contains(registroNomEsperado));
    }

    //intentar crear booking con fecha invalida
    @When("^Envio una peticion post con el payload (.+) con fechas invalidas al endpoint (.+)$")
    public void crearBookingPostFechaInv(String plCrearBookingFechaInv, String endPoint){

        File reqBodyCreateBookingInv = new File("src/test/resources/payloads/POST/" + plCrearBookingFechaInv + ".json");
        response = request
                .when()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .body(reqBodyCreateBookingInv)
                .post(endPoint)
                .prettyPeek();
    }
    @Then("^Valido el codigo de estatus sea (\\d+) Internal Server Error$")
    public void validarBookingFechaInv(int estatusCodeEsperado){
        json = response.then().statusCode(estatusCodeEsperado);
    }

    //intentar crear booking sin el valor en el campo total price
    @When("^Envio una peticion post con sin el campo totalprice (.+) al endpoint (.+)$")
    public void crearBookingPostDatoFaltante(String plCrearBookingDatoFaltante, String endPoint){

        File plCreateBookinDatoFaltante = new File("src/test/resources/payloads/POST/" + plCrearBookingDatoFaltante + ".json");
        response = request
                .when()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .body(plCreateBookinDatoFaltante)
                .post(endPoint)
                .prettyPeek();
    }
    @Then("^Valido que no se registre con el codigo de estatus (\\d+) Internal Server Error$")
    public void validarBookingDatoFaltante(int estatusCodeEsperado){
        json = response.then().statusCode(estatusCodeEsperado);
    }

    //crear un booking con total price con decimales
    @When("^Envio una peticion con el campo totalprice con decimal (.+) al endpoint (.+)$")
    public void crearBookingPostTotPrcDec(String plCrearBookingTotPricDec, String endPoint){

        File plCreateBookTotPricDecimal = new File("src/test/resources/payloads/POST/" + plCrearBookingTotPricDec + ".json");
        response = request
                .when()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .body(plCreateBookTotPricDecimal)
                .post(endPoint)
                .prettyPeek();
    }
    @Then("^valido que en la respuesta se muestre el campo totalprice (.+) y el codigo de estado (\\d+) OK$")
    public void validarBookingTotPricDec(String totalpriceEsperado, int estatusCodeEsperado){
        json = response.then().statusCode(estatusCodeEsperado);

        String jsonResponseTotalPrice = response.jsonPath().getString("booking.totalprice");
        assertTrue("El campo total price esperado " +totalpriceEsperado+ " es invalido no es igual al registrado", jsonResponseTotalPrice.contains(totalpriceEsperado));

    }

}
