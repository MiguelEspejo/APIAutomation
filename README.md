# APIAutomation
Ejercicio API automation
Se realizo el ejercicio utilizando las siguientes herramientas:
RestAssured, Java y cucumber.

Doc:
Automation Engineer Proficiency Exercise
(API AUTOMATION TEST)
Overview :
The purpose of this exercise is to assess the candidate automation
developer’s technical proficiency, coding knowledge and style. The
exercise involves building a basic automation framework for API
automation. The exercise will be evaluated on coding style,
understanding of programming concepts, choice of techniques and
quality of the final product.
Specification :
● Create an API automation framework which contains IT Industry
standards coding style.
● You can automate these endpoints using any of the automation
tools(like Rest assured, Karate preferable) in any of the
programming languages.
● Baseurl :- https://reqres.in
Description Request
Type
Endpoints Request
Body
(Payload)
Expected
Response
Code
LIST USERS GET /api/users?page
=2
Not Applicable 200
LIST <RESOURCE> GET /api/unknown Not Applicable 200
CREATE POST /api/users {"name":
"morpheus",
"job":
"leader"}
201
UPDATE PUT /api/users/2 {
"name":
"morpheus",
"job":
"zion
resident"
}
200
UPDATE PATCH /api/users/2 { 200
"name":
"morpheus",
"job":
"zion
resident"
}
DELETE DELETE /api/users/2 Not Applicable 204
REGISTER -
SUCCESSFUL
POST /api/register {
"email":
"eve.holt@reqr
es.in",
"password":
"pistol"
}
200
REGISTER -
UNSUCCESSFUL
POST /api/register {
"email":
"sydney@fife"
}
400
LOGIN SUCCESSFUL POST /api/login
{ "email":
"eve.holt@reqr
es.in",
"password":
"cityslicka"}
200
LOGIN
UNSUCCESSFUL
POST /api/login
{"email":
"peter@klaven"
}
400
Note:-
Make use of best practices for the framework like POJO. All test
scenarios should be independently tested. Try to assert things
in every scenario wherever it is possible like asserting content
type, Response code, Response Body(At least two parameters)etc.
Additional Guidelines:-
1. Comment your code where necessary.
2. Polish your code as much as possible - we expect
professional, production quality code.
3. Please commit your code on git and share the url
