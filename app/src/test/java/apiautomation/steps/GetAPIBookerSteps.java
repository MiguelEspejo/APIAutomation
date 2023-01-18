package apiautomation.steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class GetAPIBookerSteps {
    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    private String jsonResponseBookingId;

    //background
    @Given("^Ingreso a la API restful Booker para solicitar peticiones GET (.+)$")
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

    //obtener información de un ID
    @When("^Envio una solicitud para obtener la informacion del folio (\\d+) al endpoint (.+)$")
    public void getBookingById(String idRegistrado, String endPoint){
        response = request
                .when()
                .header("Accept","application/json")
                .get(endPoint+idRegistrado)
                .prettyPeek();;
    }
    @Then("^Valido que se obtenga informacion correspondiente al ID con el nombre (.+) y apellido (.+) y el codigo de estatus (\\d+) OK$")
    public void validateBookigById(String nombreEsperado, String apellidoEsperado, int statusCodeExpected){
        json = response.then().statusCode(statusCodeExpected);
        String jsonFirstName = response.jsonPath().getString("firstname");
        String jsonLastName = response.jsonPath().getString("lastname");

        assertTrue("El nombre " +nombreEsperado+ " no existe en la respuesta. ", jsonFirstName.contains(nombreEsperado));
        assertTrue("El apellido " +apellidoEsperado+ " no existe en la respuesta. ", jsonLastName.contains(apellidoEsperado));
    }

    //obtener información de un ID
    @When("^Envio una solicitud con un folio (\\d+) no registrado al endpoint (.+)$")
    public void getBookingByIdNoExistente(String idNoRegistrado, String endPoint){
        response = request
                .when()
                .header("Accept","application/json")
                .get(endPoint+idNoRegistrado)
                .prettyPeek();;
    }
    @Then("^Valido que no se obtenga informacion del ID con el codigo de estatus (\\d+) Not Found$")
    public void validateBookigByIdNoExitente(int statusCodeExpected){
        json = response.then().statusCode(statusCodeExpected);
    }

    //obtener información con nombre y apellido
    @When("^Envio una solicitud de consulta para obtener la informacion con el nombre John y apellido Smith (.+)$")
    public void getBookingByName(String endPoint){
        response = request
                .when()
                .header("Accept","application/json")
                .get(endPoint)
                .prettyPeek();;
    }
    @Then("^Valido que se obtenga la relacion de Ids que contengan el nombre y apellido y el codigo de estatus (\\d+) OK")
    public void validateBookigByName(int statusCodeExpected){
        json = response.then().statusCode(statusCodeExpected);
        List<String> getBookingsIds = response.jsonPath().getList("$");
        int sizeListBookings = getBookingsIds.size();

        boolean existenReg = true;
        if(sizeListBookings <= 0){
            existenReg = false;
        }
        assertTrue("No existen registros con esos parametros",existenReg);

    }

    //obtener información con nombre y apellido no existentes
    @When("^Envio una solicitud de consulta al servicio con nombre Roberto Carlos y apellido Soto al endpoint (.+)$")
    public void getBookingByNameNoExitente(String endPoint){
        response = request
                .when()
                .header("Accept","application/json")
                .get(endPoint)
                .prettyPeek();;
    }
    @Then("^Valido que no se obtenga relacion de ids con ese nombre y el codigo de estatus (\\d+) OK")
    public void validateBookigByNameNoExistente(int statusCodeExpected){
        json = response.then().statusCode(statusCodeExpected);
        List<String> getBookingsIds = response.jsonPath().getList("$");
        int sizeListBookings = getBookingsIds.size();

        boolean existenReg = true;
        if(sizeListBookings <= 0){
            existenReg = false;
        }
        assertFalse("Existen registros con esos parametros",existenReg);

    }

    //obtener información con nombre
    @When("^Envio una solicitud de consulta solo con el nombre John al endpoint (.+)$")
    public void getBookingBySoloNombre(String endPoint){
        response = request
                .when()
                .header("Accept","application/json")
                .get(endPoint)
                .prettyPeek();;
    }
    @Then("^Valido que se obtenga la relacion de ids correspondiente al nombre y el codigo de estatus (\\d+) OK")
    public void validateBookigBySoloNombre(int statusCodeExpected){
        json = response.then().statusCode(statusCodeExpected);
        List<String> getBookingsIds = response.jsonPath().getList("$");
        int sizeListBookings = getBookingsIds.size();

        boolean existenReg = true;
        if(sizeListBookings <= 0){
            existenReg = false;
        }
        assertTrue("No existen registros con ese parametro",existenReg);

    }

    //obtener información con apellido
    @When("^Envio una solicitud de consulta solo con el Apellido Smith al endpoint (.+)$")
    public void getBookingBySoloApellido(String endPoint){
        response = request
                .when()
                .header("Accept","application/json")
                .get(endPoint)
                .prettyPeek();;
    }
    @Then("^Valido que se obtenga la relacion de ids correspondiente al Apellido y el codigo de estatus (\\d+) OK")
    public void validateBookigBySoloApellido(int statusCodeExpected){
        json = response.then().statusCode(statusCodeExpected);
        List<String> getBookingsIds = response.jsonPath().getList("$");
        int sizeListBookings = getBookingsIds.size();

        boolean existenReg = true;
        if(sizeListBookings <= 0){
            existenReg = false;
        }
        assertTrue("No existen registros con ese parametro",existenReg);

    }

    //obtener información con un rango de fechas
    @When("^Envio una solicitud de consulta con un rango de checkin=2022-01-10&checkout=2023-01-12 al endpoint (.+)$")
    public void getBookingByFechas(String endPoint){
        response = request
                .when()
                .header("Accept","application/json")
                .get(endPoint)
                .prettyPeek();;
    }
    @Then("^Valido que se obtenga la relacion de ids registrados en el rango de fechas y el codigo de estatus (\\d+) OK")
    public void validateBookigByFechas(int statusCodeExpected){
        json = response.then().statusCode(statusCodeExpected);
        List<String> getBookingsIds = response.jsonPath().getList("$");
        int sizeListBookings = getBookingsIds.size();

        boolean existenReg = true;
        if(sizeListBookings <= 0){
            existenReg = false;
        }
        assertTrue("No existen registros con ese rango de fechas",existenReg);

    }

    //obtener información solo con fecha chekin
    @When("^Envio una solicitud solo con la fecha checkin 2022-01-10 al endpoint (.+)$")
    public void getBookingByCheckin(String endPoint){
        response = request
                .when()
                .header("Accept","application/json")
                .get(endPoint)
                .prettyPeek();;
    }
    @Then("^valido que se obtenga la relacion de bookingId registrados en la fecha de CheckIn y el codigo de estatus (\\d+) OK")
    public void validateBookigByCheckin(int statusCodeExpected){
        json = response.then().statusCode(statusCodeExpected);
        List<String> getBookingsIds = response.jsonPath().getList("$");
        int sizeListBookings = getBookingsIds.size();

        boolean existenReg = true;
        if(sizeListBookings <= 0){
            existenReg = false;
        }
        assertTrue("No existen registros esa fecha checkin",existenReg);

    }


    //obtener información solo con fecha chekout
    @When("^Envio una solicitud solo con la fecha checkout 2023-01-12 al endpoint (.+)$")
    public void getBookingByCheckout(String endPoint){
        response = request
                .when()
                .header("Accept","application/json")
                .get(endPoint)
                .prettyPeek();;
    }
    @Then("^Valido que se obtenga la relacion de bookingId registrados en la fecha de CheckOut y el codigo de estatus (\\d+) OK")
    public void validateBookigByCheckout(int statusCodeExpected){
        json = response.then().statusCode(statusCodeExpected);
        List<String> getBookingsIds = response.jsonPath().getList("$");
        int sizeListBookings = getBookingsIds.size();

        boolean existenReg = true;
        if(sizeListBookings <= 0){
            existenReg = false;
        }
        assertTrue("No existen registros con esa fecha checkout",existenReg);
    }

    //solicitar ping para ver disponibilidad del servicio
    @When("^Envio una solicitud al servicio para revisar disponibilidad al endpoint (.+)$")
    public void solicitarPing(String endPoint){
        response = request
                .when()
                .get(endPoint)
                .prettyPeek();
    }
    @Then("^Valido la disponibilidad del servicio con el codigo de estado (\\d+) Created$")
    public void validarServicioEnLinea(int estatusCodeEsperado){
        json = response.then().statusCode(estatusCodeEsperado);

    }


}
