package practice;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 extends HerOkuAppBaseUrl {

    /*
    When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking
    And Accept type is “application/JSON”
    Then HTTP Status Code should be 200
    And Response format type should be "application/JSON"
     */
    @Test
    public void get01(){

        //Set the Url
        spec.pathParam("first", "booking");

        //Set the expected Data

        //Send the request and get the response
        Response response = given().spec(spec).when().accept(ContentType.JSON).get("/{first}");
        response.prettyPrint();

        //Do Assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

    }
}
