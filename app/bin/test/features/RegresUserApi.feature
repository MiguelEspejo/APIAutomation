@testUsersOnRegresAPI
Feature: test scenarios for the API: https://reqres.in/

Rule: When sending requests to the endpoint, the response received is validated

Background: Access to request of URI
     Given I can send a request for the https://reqres.in/

@ListUser
Scenario: As a user, i can send a Get Request and receive a list of user
    When I send a GET request to the endpoint /api/users?page=2 
    Then I can validate that the user George is on the list and status 200

@ListUserInvalidPage
Scenario: As a user, i can send an invalid get request with a non-existent page and not receive user information
    When I send a GET request with invalid page parameter to the endpoint /api/users?page=245646 
    Then I can validate that the data does not exist by sending an invalid page


@SigleUser
Scenario: As a user, i can send a Get Request and receive a specific user
    When I send a GET request to get a specific user /api/users/2
    Then I can validate the Fisrt name Janet specified in the response and status code 200

@SigleUserInvalid
Scenario: As a user, i can send a Invalid user Get Request and obtain status code 404
    When I send a GET request with a non-existent user /api/users/8593
    Then I can validate that no information is obtained status code 404

@ListResource
 Scenario: As a user, i can send a Get Request and receive a Resource list
    When I send a request to get the list of Resources /api/unknown
    Then I can validate the list of 6 resources received in the response and status code 200

@CreateUser
##CreateUserPay: is a playload of the body
Scenario: As a user, I can send a Post request to create a new user
    When I send a POST request to create user with a valid body: CreateUserPay to the endpoint /api/users
    Then I can validate that the created user Roberto Carlos exists in the response and status code 201

 @UpdateUserPut
 ##UpdNameUserPay: is a playload of the body
Scenario: As a user, I can send a Put request to update a name user 
    When I send a PUT request with the body: UpdNameUserPay to update the username to the endpoint /api/users/2
    Then I can validate that the name change was made to Arturo Alarcon and the status code 200

@UpdateUserPatch
##UpdJobUserPay: is a playload of the body
Scenario: As a user, I can send a Put request to update a Job user
    When I send a PATCH request with the body: UpdJobUserPay to update the username to the endpoint /api/users/2
    Then I can validate that the job change was made to QA Engineer and the status code 200

@DeleteUser
Scenario: As a user, I can send a Delete request to erase user
    When I send a DELETE request to the endpoint /api/users/2
    Then I can validate that the user was correctly removed from the list and status code 204

@RegisterSuccessful
##RegisterUserPay: is a playload of the body
Scenario: As a user, i can send a Post request to successfully register the user
    When I send a POST request with a valid body RegisterUserPay to register to the endpoint /api/register
    Then I can validate that the user registered successfully and status code 200 

@RegisterUnsuccessful
##InvalidRegisterUserPay: is a playload of the body
Scenario: As a user, i can send a invalid Post request to register the user
    When I send a POST request to register user with an invalid password InvalidRegisterUserPay body /api/register
    Then I can validate that the error message and status code 400 

@LoginSuccessful
##LoginSucessFulPay: is a playload of the body
Scenario: As a user, I can send a Post request to login successfully
    When I send a POST request with a valid body LoginSucessFulPay for login /api/login
    Then I can validate that login is allowed successfully and status code 200    

@LoginUnsuccessful
Scenario: As a user, I can send a Invalid Post request to login
    When I send a POST request to login with an invalid body InvalidLoginPay to the endpoint /api/login
    Then I can validate that the missing password error message and the status code 400 
