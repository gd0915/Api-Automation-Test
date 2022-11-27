package get_requests_review;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;

public class Get15ReviewObjectMapperPojo extends JsonPlaceHolderBaseUrl {
    /*
    Given
    https://jsonplaceholder.typicode.com/todos/198
    When
    I send GET Request to the URL
            Then
    Status code is 200
    And response body is like {
        "userId": 10,
                "id": 198,
                "title": "quis eius est sint explicabo",
                "completed": true
    }
     */
    @Test
    public void get15() {
        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        String expectedDataInString = new JsonPlaceHolderTestData().expectedDataInString(10, "quis eius est sint explicabo", true);
        JsonPlaceHolderPojo expectedData = JsonUtils.convertJsonToJavaObject(expectedDataInString, JsonPlaceHolderPojo.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        JsonPlaceHolderPojo actualData = JsonUtils.convertJsonToJavaObject(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("actualData = "+ actualData);

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(expectedData.getUserId(), actualData.getUserId());
        Assert.assertEquals(expectedData.getTitle(), actualData.getTitle());
        Assert.assertEquals(expectedData.getCompleted(), actualData.getCompleted());

    }
}
