package practice;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get02 {

    /*
    When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/5"
    And Accept type is “application/JSON”
    Then HTTP Status Code should be 200
    And first name should be “Der”
    And total price should be 11111
     */
    @Test
    public void get02(){

        //Set the Url
        String url = "https://restful-booker.herokuapp.com/booking/5";

        //Set the expected Data

        //Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();

        //Do Assertion
        response.then().assertThat().
                statusCode(200).
                body("firstname", equalTo("Der"),
                        "totalprice", equalTo(11111));


    }
}
