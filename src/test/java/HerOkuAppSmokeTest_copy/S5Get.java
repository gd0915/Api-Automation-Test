package HerOkuAppSmokeTest_copy;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static HerOkuAppSmokeTest_copy.S1Post.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S5Get extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        User sends Get request
    Then
        Status Code must be 404
    And
        Response body should be like "Not Found"
     */
    @Test
    public void get01(){
        //Set the url
        spec.pathParams("first", "booking", "second", bookingid);

        //Set the expected Data
        String expectedData = "Not Found";

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        assertEquals(404, response.statusCode());
        assertEquals(expectedData, response.asString());

    }

}
