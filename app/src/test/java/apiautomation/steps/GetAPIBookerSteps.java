package apiautomation.steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetAPIBookerSteps {
    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    private String jsonResponseBookingId;

    //background
    @Given("^Ingreso a la API restful Booker (.+)$")
    public void accesoAlservicio(String uri){
        request = given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .baseUri(uri)
                .contentType(ContentType.JSON);
    }

    //obtener todos los datos registrados
    @When("^Envio una solicitud para obtener todos los registros del servicio al endpoint (.+)$")
    public void getBookingsIds(String endPoint){
        response = request
                .when()
                .header("Accept","application/json")
                .get(endPoint)
                .prettyPeek();;
    }
    @Then("^Valido que se obtenga informacion en la consulta y el codigo de estatus (\\d+) OK$")
    public void validateBookigsIds(int statusCodeExpected){
        json = response.then().statusCode(statusCodeExpected);
       List<String> getBookingsIds = response.jsonPath().getList("$");
       int sizeListBookings = getBookingsIds.size();

       boolean existenReg = true;
       if(sizeListBookings <= 0){
           existenReg = false;
        }
       assertTrue("No existen registros",existenReg);
    }





}
