package assignment;

import base_urls.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RegresTestData;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Assignment03 extends RegresBaseUrl {
    /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “email” is “janet.weaver@reqres.in”,
       And
           “first_name” is "Janet"
       And
           “last_name” is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */
     @Test
    public void assignment03(){
         //Set the url
         spec.pathParams("first", "users", "second", 2);

         //Set the expected data
         RegresTestData obj = new RegresTestData();
         Map<String, String> dataMap = obj.dataMapSetUp("janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg");
         Map<String, String> supportMap = obj.supportMapSetUp("https://reqres.in/#support-heading", "To keep ReqRes free, contributions towards server costs are appreciated!");
         Map<Object, Object> expectedData = obj.expectedDataSetUP(dataMap, supportMap);
         System.out.println("expectedData = " + expectedData);


         //Send the request and get the response
         Response response = given().spec(spec).when().get("/{first}/{second}");
         response.prettyPrint();

         //Do Assertion
         Map<Object, Object> actualData = response.as(HashMap.class);
         System.out.println("actualData = " + actualData);

         response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
         assertEquals(dataMap.get("email"), ((Map)actualData.get("data")).get("email"));
         assertEquals(dataMap.get("first_name"), ((Map)actualData.get("data")).get("first_name"));
         assertEquals(dataMap.get("last_name"), ((Map)actualData.get("data")).get("last_name"));
         assertEquals(supportMap.get("text"), ((Map)actualData.get("support")).get("text"));


     }
}
