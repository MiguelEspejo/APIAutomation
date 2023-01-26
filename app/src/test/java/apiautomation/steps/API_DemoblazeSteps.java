package apiautomation.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class API_DemoblazeSteps {
    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    private String jsonResponseBookingId;

    private String uri = "https://api.demoblaze.com";
    @Given("^Que puedo acceder al servicio con la URI para solicitar peticiones$")
    public void accesoAlservicio() {
        request = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .baseUri(uri)
                .contentType(ContentType.JSON);
    }

    @When("^Envio una solicitud para registrar usuario (.+) al endpoint (.+)$")
    public void crearUsuario(String pl_SignUp, String endPoint) {
        File reqBodySignUp = new File("src/test/resources/payloads/" + pl_SignUp + ".json");
        response = request
                .when()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(reqBodySignUp)
                .post(endPoint)
                .prettyPeek();
    }

    @Then("^Valido que se registre correctamente el usuario con el codigo de estatus sea (\\d+) OK$")
    public void validarEstadoCreacion(int estatusCodeEsperado) {
        json = response.then().statusCode(estatusCodeEsperado);
        //inconveniente con la validación, porque no muestra ningún mensaje solo el estatus
    }

    @When("^Envio una solicitud para obtener token de usuario (.+) al endpoint (.+)$")
    public void logIn(String pl_SignIn, String endPoint) {
        File reqBodySignIn = new File("src/test/resources/payloads/" + pl_SignIn + ".json");
        response = request
                .when()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(reqBodySignIn)
                .post(endPoint)
                .prettyPeek();
    }

    @Then("^Valido que se genere un (.+) y el codigo de estatus sea (\\d+) OK$")
    public void validarLoginToken(String token, int estatusCodeEsperado) {
        json = response.then().statusCode(estatusCodeEsperado);
        String jsonResponseToken = response.asPrettyString();
        assertTrue("La palabra " + token + " no se encuentra en la respuesta, no se genero token correstamente ", jsonResponseToken.contains(token));
    }

    @When("^Envio una solicitud para obtener token con un usuario invalido (.+) al endpoint (.+)$")
    public void logInInv(String pl_SignInInv, String endPoint) {
        File reqBodySignInInv = new File("src/test/resources/payloads/" + pl_SignInInv + ".json");
        response = request
                .when()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(reqBodySignInInv)
                .post(endPoint)
                .prettyPeek();
    }

    @Then("^Valido que no se genere token y muestre el siguiente mensaje (.+) y el codigo de estatus sea (\\d+) OK$")
    public void validarLoginTokenInv(String mensajeVal, int estatusCodeEsperado) {
        json = response.then().statusCode(estatusCodeEsperado);
        String jsonResponseToken = response.asPrettyString();
        assertTrue("El mensaje  " + mensajeVal + " no se encuentra en la respuesta ", jsonResponseToken.contains(mensajeVal));
    }

    @When("^Envio una solicitud para obtener token con contrasena incorrecta (.+) al endpoint (.+)$")
    public void logInInvPass(String pl_SignInInv, String endPoint) {
        File reqBodySignInInv = new File("src/test/resources/payloads/" + pl_SignInInv + ".json");
        response = request
                .when()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(reqBodySignInInv)
                .post(endPoint)
                .prettyPeek();
    }

    @Then("^Valido que no se genere token y cuando se envie contrasena invalida se muestre el siguiente mensaje (.+) y el codigo de estatus sea (\\d+) OK$")
    public void validarLoginTokenInvPass(String mensajeVal, int estatusCodeEsperado) {
        json = response.then().statusCode(estatusCodeEsperado);
        String jsonResponseToken = response.asPrettyString();
        assertTrue("El mensaje  " + mensajeVal + " no se encuentra en la respuesta ", jsonResponseToken.contains(mensajeVal));
    }


}
