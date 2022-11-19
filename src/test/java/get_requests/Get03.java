package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {
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
    public void get03() {
        //Set the URL
        spec.pathParams("first", "todos", "second", 23);

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1st Way: Hard Assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("userId", equalTo(2)).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false));

        //Note: For example, if the execution stop on the 46th line because of a failure, the execution will stop, and we
        //will be not able to see what happens on the 47th and 48th line if they pass or not.

        //2nd Way: Soft Assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "userId", equalTo(2),
                        "completed", equalTo(false));

        //  Note: For example, we get a failure on the 46th line , the execution will not stop, and we
        //        will be able to see what happens on the 47th and 48th line if they pass or not.

        /*
        Note 1: When you execute assertion java stops execution after the first failure.
                It means, assertion after the failure were not executed.
                But the assertions before the failure were passed, because assertion before the failure were executed.
        Note 2: If you type your code as execution will stop in the first failure this is called "Hard Assertion".
        Note 3: If you type your code as execution will continue after the failure this is called "Soft Assertion".
        Note 3: If you use multiple "body()" method it will work like "Hard Assertion",
                but if you use just single "body()" method with multiple assertions this is called "Soft Assertion".
         */
    }

}
