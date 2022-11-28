package HerOkuAppSmokeTest_copy;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static HerOkuAppSmokeTest_copy.S1Post.bookingid;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class S4Delete extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        User sends Delete request
   Then
        Status Code must be 201
    And
        Response body should be "Created"

     */
    @Test
    public void Delete01(){
        //Set the url
        spec.pathParams("first", "booking", "second", bookingid);

        //Set the expected data
        String expectedData = "Created";

        //Send the request and get the response
        Response response = given().
                spec(spec).
                contentType(ContentType.JSON).
                headers("Cookie", "token="+generateToken()).
                when().
                delete("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        assertEquals(201, response.statusCode());
        assertEquals("Created", response.asString());


    }
}
