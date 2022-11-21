package assignment;

import base_urls.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RegresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Assignment01 extends RegresBaseUrl {
    /*
        Given
            https://reqres.in/api/users/3
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
    public void assignment01(){
        //Set the url
        spec.pathParams("first", "users", "second", 3);

        //Set the expected data
        RegresTestData obj = new RegresTestData();
        Map<String, String> dataMap = obj.dataMapSetUp("emma.wong@reqres.in", "Emma", "Wong", "https://reqres.in/img/faces/3-image.jpg");
        Map<String, String> supportMap = obj.supportMapSetUp("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!");
        Map<Object, Object> expectedData = obj.expectedDataSetUP(dataMap, supportMap);
        System.out.println("expected = " + expectedData);

        //Send the request and Get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<Object, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");





    }
}
