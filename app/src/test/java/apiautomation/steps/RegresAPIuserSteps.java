package apiautomation.steps;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

public class RegresAPIuserSteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;
    
    //background
    @Given("^I can send a request for the (.+)$")
    public void sendReqGetUri(String uri){
        request = given()
        .baseUri(uri)
        .contentType(ContentType.JSON);
    }

    //TagListUser
    @When("^I send a GET request to the endpoint (.+)$")
    public void sendReqGetUSerList(String endPoint){
        response = request 
            .when()
            .get(endPoint);
    }
    @Then("^I can validate that the user (.+) is on the list and status (\\d+)$")
    public void validateRespGet(String expectedUser, int statusCodeExpected){

        json = response.then().statusCode(statusCodeExpected);
        //validate user exist in the list
        String listNames = response.jsonPath().getString("data.first_name");
        assertTrue("The user " +expectedUser+ " don't exist in the user list. ", listNames.contains(expectedUser));
    }

      //TagListUserInvalidPage
      @When("^I send a GET request with invalid page parameter to the endpoint (.+)$")
      public void sendReqGetUSerListInvalid(String endPoint){
          response = request 
              .when()
              .get(endPoint);
      }
      @Then("^I can validate that the data does not exist by sending an invalid page$")
      public void validateInvalidPage(){
          //validate data empty i get data node and validete
          List<String> dataInfo = response.jsonPath().getList("data");
          int sizeOfData = dataInfo.size();
          assertEquals("Information was obtained with the pagination",0,sizeOfData);
      }
 
      //TagSingleUser
      @When("^I send a GET request to get a specific user (.+)$")
      public void getSpecificUser(String endPoint){
          response = request 
              .when()
              .get(endPoint);
      }
      @Then("^I can validate the Fisrt name (.+) specified in the response and status code (\\d+)$")
      public void validateSpecificUser(String expectedFirstName, int expectedStatusCode){
              //validate name of spefic user 2
            json = response.then().statusCode(expectedStatusCode);
            String jsonFirstName = response.jsonPath().getString("data.first_name");
            assertTrue("The user " +expectedFirstName+ " don't exist. ", jsonFirstName.contains(expectedFirstName));
      }

        //TagSingleUserInvalid
        @When("^I send a GET request with a non-existent user (.+)$")
        public void getSpecificUserInvalid(String endPoint){
            response = request 
                .when()
                .get(endPoint);
        }
        @Then("^I can validate that no information is obtained status code (\\d+)$")
        public void validateSigleUserStatusCode(int expectedStatusCode){
              json = response.then().statusCode(expectedStatusCode);
    
        }

        //TagResourceList
        @When("^I send a request to get the list of Resources (.+)$")
        public void getResourceList(String endPoint){
            response = request 
                .when()
                .get(endPoint);
        }
        @Then("^I can validate the list of (\\d+) resources received in the response and status code (\\d+)$")
        public void validateCantResources(int expectedNumResult, int expectedStatusCode){
            json = response.then().statusCode(expectedStatusCode);
            //validate number of results
            List<String> dataResources = response.jsonPath().getList("data");
            int sizeOfResources = dataResources.size();
            assertEquals(" Less than expected results were obtained.",expectedNumResult,sizeOfResources);

        }

        //TagCreateUser
        @When("^I send a POST request to create user with a valid body: (.+) to the endpoint (.+)$")
        public void createUserPost(String createUserPlayload, String endPoint){
            //the way to load the payloads from cucumber is placed dynamically sending the name of the file
            File reqBodyCreateUser = new File("src/test/resources/payloads/" + createUserPlayload + ".json");
            response = request 
                .when()
                .body(reqBodyCreateUser)
                .post(endPoint)
                .prettyPeek();
        }
        @Then("^I can validate that the created user (.+) exists in the response and status code (\\d+)$")
        public void validateUserCreated(String expectedUserCreated, int expectedStatusCode){
            json = response.then().statusCode(expectedStatusCode);
            //I validate that the name of the user created in the response because the api doesn't actually create users
            String jsonResponseName = response.jsonPath().getString("name");
            assertTrue("The user " +expectedUserCreated+ " don't exist. ", jsonResponseName.contains(expectedUserCreated));  
        }

        //tagUpdateUserPut
        @When("^I send a PUT request with the body: (.+) to update the username to the endpoint (.+)$")
        public void updateUserPut(String updateUserPayload, String endPoint){
            //the way to load the payloads from cucumber is placed dynamically sending the name of the file
            File reqBodyUpdateUser = new File("src/test/resources/payloads/" + updateUserPayload + ".json");
            response = request 
                .when()
                .body(reqBodyUpdateUser)
                .put(endPoint)
                .prettyPeek();
        }
        @Then("^I can validate that the name change was made to (.+) and the status code (\\d+)$")
        public void validateUpdateUser(String expectedUpdatedName, int expectedStatusCode){
            json = response.then().statusCode(expectedStatusCode);
            //I validate that the name of the update user in the response because the api doesn't actually update users
            String jsonResponseName = response.jsonPath().getString("name");
            assertTrue("The user " +expectedUpdatedName+ " don't exist. ", jsonResponseName.contains(expectedUpdatedName));  
        }

         //tagUpdateUserPatch
         @When("^I send a PATCH request with the body: (.+) to update the username to the endpoint (.+)$")
         public void updateUserJobPatch(String updateUserJobPayload, String endPoint){
             //the way to load the payloads from cucumber is placed dynamically sending the name of the file
             File reqBodyUpdateUser = new File("src/test/resources/payloads/" + updateUserJobPayload + ".json");
             response = request 
                 .when()
                 .body(reqBodyUpdateUser)
                 .patch(endPoint)
                 .prettyPeek();
         }
         @Then("^I can validate that the job change was made to (.+) and the status code (\\d+)$")
         public void validateUpdateJobUser(String expectedUpdatedJob, int expectedStatusCode){
             json = response.then().statusCode(expectedStatusCode);
             //I validate that the job of the update job in the response because the api doesn't actually update users
             String jsonResponseJob = response.jsonPath().getString("job");
             assertTrue("The Job user " +expectedUpdatedJob+ " don't exist. ", jsonResponseJob.contains(expectedUpdatedJob));  
         }
        
    //TagDeleteUser
    @When("^I send a DELETE request to the endpoint (.+)$")
    public void sendDeleteUSer(String endPoint){
        response = request 
            .when()
            .delete(endPoint);
    }
    @Then("^I can validate that the user was correctly removed from the list and status code (\\d+)$")
    public void validateDeleteStatusCode(int statusCodeExpected){
        json = response.then().statusCode(statusCodeExpected);
       
    }

    //TagRegisterSuccessful
    @When("^I send a POST request with a valid body (.+) to register to the endpoint (.+)$")
    public void reqToRegister(String registerPlayload, String endPoint){
        //the way to load the payloads from cucumber is placed dynamically sending the name of the file
        File reqBodyRegister = new File("src/test/resources/payloads/" + registerPlayload + ".json");
        response = request 
            .when()
            .body(reqBodyRegister)
            .post(endPoint)
            .prettyPeek();
    }
    @Then("^I can validate that the user registered successfully and status code (\\d+)$")
    public void validateRegister(int expectedStatusCode){
        json = response.then().statusCode(expectedStatusCode);
        //validated with the token string because in this api it does not change / It can be improved
        String jsonResponseToken = response.jsonPath().getString("token");
        assertTrue("The user not Registered. ", jsonResponseToken.contains("QpwL5tke4Pnpja7X4")); 
    }

    //TagRegisterUnsuccessful
    @When("^I send a POST request to register user with an invalid password (.+) body (.+)$")
    public void reqToInvalidRegister(String invalidRegisterPlayload, String endPoint){
        File reqBodyInvalidRegister = new File("src/test/resources/payloads/" + invalidRegisterPlayload + ".json");
        response = request 
            .when()
            .body(reqBodyInvalidRegister)
            .post(endPoint)
            .prettyPeek();
    }
    @Then("^I can validate that the error message and status code (\\d+)$")
    public void validateInvalidRegister(int expectedStatusCode){
        json = response.then().statusCode(expectedStatusCode);
        String jsonResponseError = response.jsonPath().getString("error");
        assertTrue("The correct validation message is not being displayed. ", jsonResponseError.contains("Missing password")); 
    }

     //TagLoginSuccessful
     @When("^I send a POST request with a valid body (.+) for login (.+)$")
     public void reqToLogin(String loginPlayload, String endPoint){
         File reqBodyLogin = new File("src/test/resources/payloads/" + loginPlayload + ".json");
         response = request 
             .when()
             .body(reqBodyLogin)
             .post(endPoint)
             .prettyPeek();
     }
     @Then("^I can validate that login is allowed successfully and status code (\\d+)$")
     public void validateLogin(int expectedStatusCode){
         json = response.then().statusCode(expectedStatusCode);
         String jsonResponseToken = response.jsonPath().getString("token");
         assertTrue("The email or password is invalid. ", jsonResponseToken.contains("QpwL5tke4Pnpja7X4")); 
     }

     //TagInvalidLogin
    @When("^I send a POST request to login with an invalid body (.+) to the endpoint (.+)$")
    public void reqToInvalidLogin(String invalidRegisterPlayload, String endPoint){
        File reqBodyInvalidLogin = new File("src/test/resources/payloads/" + invalidRegisterPlayload + ".json");
        response = request 
            .when()
            .body(reqBodyInvalidLogin)
            .post(endPoint)
            .prettyPeek();
    }
    @Then("^I can validate that the missing password error message and the status code (\\d+)$")
    public void validateInvalidLogin(int expectedStatusCode){
        json = response.then().statusCode(expectedStatusCode);
        String jsonResponseError = response.jsonPath().getString("error");
        assertTrue("The correct validation message is not being displayed. ", jsonResponseError.contains("Missing password")); 
    }
}
