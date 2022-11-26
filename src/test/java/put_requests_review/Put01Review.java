package put_requests_review;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Put01Review extends JsonPlaceHolderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */
    @Test
    public void put01Review(){
            //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //1st Way: Pojo Class
            //Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(21,"Wash the dishes", false );
        System.out.println("expectedData = " + expectedData);

            //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

            //Do Assertion
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());

        //2nd Way: Map
            //Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData01 = obj.expectedDataJPH(21,"Wash the dishes", false);
        System.out.println("expectedData01 = " + expectedData01);

            //Send the request and get the response
        Response response01 = given().spec(spec).contentType(ContentType.JSON).body(expectedData01).when().put("/{first}/{second}");
        response01.prettyPrint();

            //Do Assertion
        Map<String, Object> actualData01 = response01.as(HashMap.class);
        System.out.println("actualData01 = " + actualData01);

        assertEquals(200, response01.statusCode());
        assertEquals(expectedData01.get("userId"), actualData01.get("userId"));
        assertEquals(expectedData01.get("title"), actualData01.get("title"));
        assertEquals(expectedData01.get("completed"), actualData01.get("completed"));


    }
}
