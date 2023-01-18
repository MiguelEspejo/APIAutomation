package apiautomation.steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class PutPatchAPIBookerSteps {
    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    private String jsonResponseBookingId;


    //background
    @Given("^Ingreso a la API restful Booker para realizar peticiones PUT-PATCH (.+)$")
    public void accesoAlservicio(String uri){
        request = given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .baseUri(uri)
                .contentType(ContentType.JSON);
    }

    //creo un registro insumo para poder actualizar
    @When("^Envio una peticion al endpoint (.+) para crear un registro de insumo para poder actualizar (.+)$")
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
    @Then("^Valido que se cree correctamente registro y valido el codigo de estatus (\\d+) OK$")
    public void validarBookingCreado(int estatusCodeEsperado){
        json = response.then().statusCode(estatusCodeEsperado);
        jsonResponseBookingId = response.jsonPath().getString("bookingid");
       //assertTrue("El booking del usuario " +registroNomEsperado+ " no fue creado, no existe en la respuesta ", jsonResponsefirstName.contains(registroNomEsperado));
        System.out.println("booking ID "+jsonResponseBookingId);
    }

    //actualizando el registro creado
    @When("^Envio una solicitud para actualizar la informacion del Booking (.+) al endpoint (.+)$")
    public void updBookingCorrecto(String updBookingPayload, String endPoint){
        File reqBodyUpdateBooking = new File("src/test/resources/payloads/PUT-PATCH/" + updBookingPayload + ".json");
        String bookingIdCreado = jsonResponseBookingId;
        response = request
                .when()
                .auth()
                .preemptive()
                .basic("admin","password123")
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token=<token_value>")
                .header("Authorizationo","Basic")
                .body(reqBodyUpdateBooking)
                .put(endPoint+bookingIdCreado)
                .prettyPeek();
    }
    @Then("^valido que se actualice correctamente el nombre (.+) el apellido (.+) y el precio (\\d+) y el codigo de estatus (\\d+) OK$")
    public void validateUpdBooking(String nombreEsperado, String apellidoEsperado, String precioEsperado, int expectedStatusCode){
        json = response.then().statusCode(expectedStatusCode);

        String jsonResponseFirstName = response.jsonPath().getString("firstname");
        String jsonResponseLastName = response.jsonPath().getString("lastname");
        String jsonResponseTotalPrice = response.jsonPath().getString("totalprice");
        assertTrue("El Nombre " +nombreEsperado+ " no fue actualizado correctamente en el registro ", jsonResponseFirstName.contains(nombreEsperado));
        assertTrue("El Apellido " +apellidoEsperado+ " no fue actualizado correctamente en el registro ", jsonResponseLastName.contains(apellidoEsperado));
        assertTrue("El Precio " +precioEsperado+ " no fue actualizado correctamente en el registro ", jsonResponseTotalPrice.contains(precioEsperado));
    }
    //update con fechas invalidas
    @When("^Envio una solicitud para actualizar con fechas invalidas (.+) al endpoint (.+)$")
    public void updBookingInv(String updBookingPayloadInv, String endPoint){
        File reqBodyUpdateBooking = new File("src/test/resources/payloads/PUT-PATCH/" + updBookingPayloadInv + ".json");
        String bookingIdCreado = jsonResponseBookingId;
        response = request
                .when()
                .auth()
                .preemptive()
                .basic("admin","password123")
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token=<token_value>")
                .header("Authorizationo","Basic")
                .body(reqBodyUpdateBooking)
                .put(endPoint+bookingIdCreado)
                .prettyPeek();
    }
    @Then("^Valido que no se ejecute la actualizacion con el codigo de estatus (\\d+) Internal Server Error$")
    public void validateUpdBookingInv(int expectedStatusCode){
        json = response.then().statusCode(expectedStatusCode);
    }
   //update sin token
   @When("^Envio una solicitud de actualizacion sin contar con un token valido (.+) al endpoint (.+)$")
   public void updBookingSinToken(String updBookingPayload, String endPoint){
       File reqBodyUpdateBooking = new File("src/test/resources/payloads/PUT-PATCH/" + updBookingPayload + ".json");
       String bookingIdCreado = jsonResponseBookingId;
       response = request
               .when()
               .auth()
               .preemptive()
               .basic("administrador","contrasena")
               .header("Content-Type","application/json")
               .header("Accept","application/json")
               .header("Cookie","token=<token_value>")
               .header("Authorizationo","Basic")
               .body(reqBodyUpdateBooking)
               .put(endPoint+bookingIdCreado)
               .prettyPeek();
   }
    @Then("^valido el codigo de estatus (\\d+) Forbidden al intentar realizar una actualizacion sin token$")
    public void validateUpdBookingSinToken(int expectedStatusCode){
        json = response.then().statusCode(expectedStatusCode);
    }

    //PATCH

    @When("^Envio una solicitud de actualizacion con solo el nombre y apellido de la persona (.+) al endpoint (.+)$")
    public void patchBookingCorrecto(String patchBookingPayload, String endPoint){
        File reqBodypatchateBooking = new File("src/test/resources/payloads/PUT-PATCH/" + patchBookingPayload + ".json");
        String bookingIdCreado = jsonResponseBookingId;
        response = request
                .when()
                .auth()
                .preemptive()
                .basic("admin","password123")
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token=<token_value>")
                .header("Authorizationo","Basic")
                .body(reqBodypatchateBooking)
                .patch(endPoint+bookingIdCreado)
                .prettyPeek();
    }
    @Then("^Valido que el nombre actualizado sea (.+) y el apellido actualizado sea (.+) y el codigo de estatus (\\d+) OK$")
    public void validateUpdBookingPatchCorrecto(String nombreEsperado, String apellidoEsperado, int expectedStatusCode){
        json = response.then().statusCode(expectedStatusCode);

        String jsonResponseFirstName = response.jsonPath().getString("firstname");
        String jsonResponseLastName = response.jsonPath().getString("lastname");
        assertTrue("El Nombre " +nombreEsperado+ " no fue actualizado correctamente en el registro ", jsonResponseFirstName.contains(nombreEsperado));
        assertTrue("El Apellido " +apellidoEsperado+ " no fue actualizado correctamente en el registro ", jsonResponseLastName.contains(apellidoEsperado));

    }

    //patc sin token
    @When("^Envio una solicitud de actualizacion de nombre y apellido sin token (.+) al endpoint (.+)$")
    public void patchBookingSinToken(String patchBookingPayload, String endPoint){
        File reqBodyPatchBooking = new File("src/test/resources/payloads/PUT-PATCH/" + patchBookingPayload + ".json");
        String bookingIdCreado = jsonResponseBookingId;
        response = request
                .when()
                .auth()
                .preemptive()
                .basic("administrador","contrasena")
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token=<token_value>")
                .header("Authorizationo","Basic")
                .body(reqBodyPatchBooking)
                .patch(endPoint+bookingIdCreado)
                .prettyPeek();
    }
    @Then("^Valido que no se actualice la informacion con el codigo de estatus (\\d+) Forbidden$")
    public void validatePatchdBookingSinToken(int expectedStatusCode){
        json = response.then().statusCode(expectedStatusCode);
    }





}
