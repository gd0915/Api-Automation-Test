package assignment;

import base_urls.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.RegresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Assignment05 extends RegresBaseUrl{
     /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */
    @Test
    public void assignment05(){
        //Set the url
        spec.pathParams("first", "unknown", "second", 3);

        //Set the expected data
//        RegresTestData obj = new RegresTestData();
//        Map<String, Object> dataMap = obj.dataMapSetUp2("true red", 2002, "#BF1932","19-1664");
//        Map<String, String> supportMap = obj.supportMapSetUp("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!");
//        Map<Object, Object> expectedData = obj.expectedDataSetUP2(dataMap, supportMap);
//        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
//        Map<Object, Object> actualData = new HashMap<>();
//        System.out.println("actualData = " + actualData);

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        JsonPath jsonPath = response.jsonPath();//de-serialization
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(jsonPath.getString("data.name"), "true red", "name did not match");
        softAssert.assertEquals(jsonPath.getInt("data.year"), 2002, "year did not match");
        softAssert.assertEquals(jsonPath.getString("data.color"), "#BF1932", "color did not match");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"), "19-1664", "pantone_value did not match");
        softAssert.assertEquals(jsonPath.getString("support.url"), "https://reqres.in/#support-heading", "url did not match");
        softAssert.assertEquals(jsonPath.getString("support.text"), "To keep ReqRes free, contributions towards server costs are appreciated!", "text did not match");

        softAssert.assertAll();



    }

}
