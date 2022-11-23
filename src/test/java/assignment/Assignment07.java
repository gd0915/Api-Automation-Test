package assignment;

import base_urls.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.RegresPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Assignment07 extends RegresBaseUrl {
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */
    @Test
    public void assignment07(){
        //Set the url
        spec.pathParam("first", "users");

        //Set the expected data
        RegresPojo expectedData = new RegresPojo("morpheus", "leader");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).post("/{first}");
        response.prettyPrint();

        //Do Assertion
        RegresPojo actualData = response.as(RegresPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getJob(), actualData.getJob());







    }


}
