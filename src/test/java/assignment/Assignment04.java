package assignment;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.testng.AssertJUnit.assertTrue;

public class Assignment04 extends HerOkuAppBaseUrl {
/*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Elena&lastname=Zaitsava
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Elena" and lastname is "Zaitsava"

 */
    @Test
        public void assignment04() {
        //Set the url
        spec.pathParam("first", "booking").
                queryParams("firstname", "Elena", "second", "Zaitsava");

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        response.then().assertThat().statusCode(200);
        assertTrue(response.asString().contains("bookingid"));



    }






}
