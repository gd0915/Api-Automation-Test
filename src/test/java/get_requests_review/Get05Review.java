package get_requests_review;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get05Review extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "James" and lastname is "Brown"
 */

    @Test
    public void get05Review(){

        //Set the Url
        spec.pathParam("first", "booking").
                queryParams("first", "James","second", "Brown");

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        //1st Way:
        response.then().
                assertThat().
                statusCode(200);

        //2nd Way:
        assertEquals(200, response.statusCode());

        //Among the data there should be someone whose firstname is "James" and lastname is "Brown"
        assertTrue(response.asString().contains("bookingid"));




    }





}
