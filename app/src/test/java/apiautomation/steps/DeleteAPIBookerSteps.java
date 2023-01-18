package apiautomation.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class DeleteAPIBookerSteps {
    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    private String jsonResponseBookingId;

    //background
    @Given("^Ingreso a la API restful Booker para realizar peticiones GET (.+)$")
    public void accesoAlservicio(String uri){
        request = given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .baseUri(uri)
                .contentType(ContentType.JSON);
    }

    //creo un registro insumo para delete
    @When("^Envio una peticion al endpoint (.+) para crear un registro de insumo para eliminarlo (.+)$")
    public void crearBookingPost(String endPoint, String createBookingPayload){
        File reqBodyCreateBooking = new File("src/test/resources/payloads/POST/" + createBookingPayload + ".json");
        response = request
                .when()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .body(reqBodyCreateBooking)
                .post(endPoint)
                .prettyPeek();
    }
    @Then("^Valido que se cree correctamente el insumo y valido el codigo de estatus (\\d+) OK$")
    public void validarBookingCreado(int estatusCodeEsperado){
        json = response.then().statusCode(estatusCodeEsperado);
        jsonResponseBookingId = response.jsonPath().getString("bookingid");
        //assertTrue("El booking del usuario " +registroNomEsperado+ " no fue creado, no existe en la respuesta ", jsonResponsefirstName.contains(registroNomEsperado));
        System.out.println("booking ID "+jsonResponseBookingId);
    }

    //delete correcto
    @When("^Envio una solicitud para borrar un registro al endpoint (.+)$")
    public void delBookingCorrecto(String endPoint){
        String bookingIdCreado = jsonResponseBookingId;
        response = request
                .when()
                .auth()
                .preemptive()
                .basic("admin","password123")
                .delete(endPoint+bookingIdCreado)
                .prettyPeek();
    }
    @Then("^Valido que se borre correctamente con el codigo de estatus (\\d+) Created$")
    public void validateDelBooking(int expectedStatusCode){
        json = response.then().statusCode(expectedStatusCode);

    }

    //Delete con id no registrado
    @When("^Envio una peticion delete con un token valido y con un id invalido al endpoint (.+)$")
    public void delBookingIdInv(String endPoint){
        response = request
                .when()
                .auth()
                .preemptive()
                .basic("admin","password123")
                .delete(endPoint)
                .prettyPeek();
    }
    @Then("^Valido que se muestre el codigo de estatus (\\d+) Method Not Allowed$")
    public void validateDelBookingIdInv(int expectedStatusCode){
        json = response.then().statusCode(expectedStatusCode);
    }

    //delete sin token
    @When("^Envio una solicitud delete con un token incorrecto y al endpoint (.+)$")
    public void delBookingSinToken(String endPoint){
        String bookingIdCreado = jsonResponseBookingId;
        response = request
                .when()
                .auth()
                .preemptive()
                .basic("administrador","password")
                .delete(endPoint+bookingIdCreado)
                .prettyPeek();
    }
    @Then("^Valido que no se permita eliminar sin token con el codigo de estatus (\\d+) Forbidden$")
    public void validateDelBookingSinToken(int expectedStatusCode){
        json = response.then().statusCode(expectedStatusCode);
    }




}
