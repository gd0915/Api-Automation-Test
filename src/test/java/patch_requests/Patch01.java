package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Read the books"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */
    @Test
    public void patch01(){
        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        //JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(null, "Read the books", null);
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataJPH(null, "Read the books", null);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response =  given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(10, actualData.get("userId"));
        assertEquals(true, actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));

        //or
        response.then().
                assertThat().
                body("userId", equalTo(10),
                "title", equalTo("Read the books"),
                        "completed", equalTo(true));
    }
}
