package delete_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Delete02_copy extends HerOkuAppBaseUrl {
/*
        Given
            https://restful-booker.herokuapp.com/booking/{bookingId}
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is "Created"
     */
    @Test
    public void delete02(){
        //Set the url
        spec.pathParams("first", "booking", "second", 22910);

        //Set the expected data
        String expectedData = "Created";

        //Send the request and get the response
        Response response = given().
                                spec(spec).
                                headers("Cookie", "token=1d2209034d5e437").
                                when().
                                delete("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        assertEquals(201, response.statusCode());
        assertEquals(expectedData, response.asString());





    }
}
