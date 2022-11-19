package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashSet;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class Get04 extends JsonPlaceHolderBaseUrl {
    /*
   Given
       https://jsonplaceholder.typicode.com/todos
   When
  I send a GET request to the Url
And
    Accept type is "application/json"
Then
    HTTP Status Code should be 200
And
    Response format should be "application/json"
And
    There should be 200 todos
And
    "quis eius est sint explicabo" should be one of the todos title
And
    2, 7, and 9 should be among the userIds
*/

    @Test
    public void get04(){
        //Set the URL
        spec.pathParam("first", "todos");

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
       response.prettyPrint();

       //Do Assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", hasSize(200), "title",hasItem("quis eius est sint explicabo"),"userId",hasItems(2,7,9));

        // hasSize() method is taking all the ID's inside the list, so hasSize() method is counting how many elements are inside the "id" list
        //If we do not type anything inside "" it will count all the Jason's(elements)(all todos)
        // hasItem() method is checking if the body has that item(if there is that item inside the "title" list)
        // hasItems() method is checking if the body has that multiple data

    }





}
