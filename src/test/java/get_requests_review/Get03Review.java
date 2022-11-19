package get_requests_review;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03Review extends JsonPlaceHolderBaseUrl {
    /*
       Given
           https://jsonplaceholder.typicode.com/todos/23
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
   And
       Response format should be "application/json"
   And
       "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
   And
       "completed" is false
   And
       "userId" is 2
    */

    @Test
    public void get03Review(){
        //Set the Url
        spec.pathParams("first", "todos", "second", 23);

        //Set the data

        //Send the request and get the response
        Response response  = given().spec(spec).when().get("/{first}/{second}");

        //Do Assertion
        //1st Way:Hard Assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).body("userId", equalTo(2));

        //2nd Way:Soft Assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false), "userId", equalTo(2));













    }















}
