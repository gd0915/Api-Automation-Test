package get_requests_review;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Review extends GoRestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users/3808
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
               {
                    "meta": null,
                    "data": {
                        "id": 3808,
                        "name": "Shubha Saini",
                        "email": "shubha_saini@strosin.biz",
                        "gender": "male",
                        "status": "inactive"
                        }
                    }

     */
    @Test
    public void get13Review(){
        //Set the url
        spec.pathParams("first", "users", "second", 3808);

        //Set the expected data
        GoRestDataPojo dataPojo = new GoRestDataPojo("Shubha Saini","shubha_saini@strosin.biz", "male", "inactive" );
        GoRestPojo expectedData = new GoRestPojo(null, dataPojo);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getMeta(), actualData.getMeta());
        assertEquals(dataPojo.getName(), actualData.getData().getName());
        assertEquals(dataPojo.getEmail(), actualData.getData().getEmail());
        assertEquals(dataPojo.getGender(), actualData.getData().getGender());
        assertEquals(dataPojo.getStatus(), actualData.getData().getStatus());






    }













}
