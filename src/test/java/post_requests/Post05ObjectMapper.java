package post_requests;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiPojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class Post05ObjectMapper extends DummyApiBaseUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */
    /*
    (Test Case in Gherkin Language:)
    Given
        https://dummy.restapiexample.com/api/v1/create
    And
    Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
     When
        User sends the Post request
     Then
        Status code is 200
     And
        Response body should be like the following
                {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
             */

    @Test
    public void post05ObjectMapper() throws IOException {
        //Set the Url
        spec.pathParam("first", "create");

        //Set the expected data
        DummyApiDataPojo expectedData  = new DummyApiDataPojo("Ali Can", 111111, 23, "Perfect image");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}"); //restAssured(library) methods
        response.prettyPrint();

        //Do Assertion (ObjectMapper Class from the restAssured library)

                // DummyApiPojo actualData = response.as(DummyApiPojo.class);
                //System.out.println("actualData = " + actualData);

        DummyApiPojo actualData = new ObjectMapper().readValue(response.asString() , DummyApiPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals("success", actualData.getStatus());
        assertEquals(expectedData.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(), actualData.getData().getProfile_image());
        assertEquals("Successfully! Record has been added.", actualData.getMessage());







    }
}
