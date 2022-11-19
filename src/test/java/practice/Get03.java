package practice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get03 {
    /*
    When I send a GET request to REST API URL http://dummy.restapiexample.com/api/v1/employees
    And Accept type is “application/JSON”
    Then HTTP Status Code should be 200
    And “Gerret Winters” should be displayed among data

     */

    @Test
    public void get03(){
        //Set the Url
        String url = "http://dummy.restapiexample.com/api/v1/employees";

        //Set the expected Data

        //Send the request and get the response
        Response response = given().when().accept(ContentType.JSON).get(url);
        response.prettyPrint();

        //Do Assertion
        response.then().assertThat().statusCode(200);






    }
}
