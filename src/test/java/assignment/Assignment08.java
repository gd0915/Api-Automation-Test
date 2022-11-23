package assignment;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PetStorePojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Assignment08 extends PetStoreBaseUrl {
    //Homework8:

   /*
    Type automation code to create a 'user' by using "https://petstore.swagger.io/"" documentation.

    Given https://petstore.swagger.io/v2/user
    https://petstore.swagger.io/v2/user/createWithList
    2)
        {
                "id": 6874986542,
                "username": "elif",
                "firstName": "elif kaya",
                "lastName": "atsiz",
                "email": "atsiz@gmail.com",
                "password": "string",
                "phone": "123456789",
                "userStatus": 123
    }

    I send POST Request to the Url
            Then
    Status code is 201
    Content Type is application/json
    Server is Jetty(9.2.9.v20150224)

    */

    @Test
    public void homework08_Create() {
        //Set the url
        spec.pathParams("first","v2","second","user");

        //Set the expected data

        PetStorePojo expectedData = new PetStorePojo("elif","elif kaya","atsiz","atsiz@gmail.com","string","123456789",123);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}/{second}");
        response.prettyPrint();

//        Response response1 = given().spec(spec).when().get("/{first}/{second}/{third}");
//        response1.prettyPrint();

        //Do Assertion
        assertEquals(200, response.statusCode());
        //assertEquals(200, response1.statusCode());








    }



}