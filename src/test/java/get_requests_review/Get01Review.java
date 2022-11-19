package get_requests_review;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01Review {
     /*
   Given
       https://restful-booker.herokuapp.com/booking/10
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/

    @Test
    public void get01Review(){
        //Set the Url
        String url ="https://restful-booker.herokuapp.com/booking/10";

        //Set the data

        //Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();

        //Do Assertion
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");

        System.out.println("Status Code: "+response.statusCode());
        System.out.println("Content-Type: "+response.contentType());
        System.out.println("Status Line: "+response.statusLine());

        System.out.println(response.getHeader("Server"));
        System.out.println(response.getHeader("Content-Type"));

        System.out.println(response.getHeaders());

        System.out.println(response.getTime());


    }

}
