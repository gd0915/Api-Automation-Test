package get_requests_review;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Get06Review extends HerOkuAppBaseUrl {
      /*
   Given
       https://restful-booker.herokuapp.com/booking/2
   When
       User send a GET request to the URL
   Then
       HTTP Status Code should be 200
   And
       Response content type is "application/json"
   And
       Response body should be like;
{
    "firstname": "James",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {                       ==>nested Json data
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
 */

    @Test
    public void get06Review(){
        //Set the Url
        spec.pathParams("first", "booking", "second", 2);

        //Set the expected Data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion

        //1st Way:Soft Assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                    body("firstname", equalTo("James"),
                            "lastname", equalTo("Brown"),
                            "totalprice", equalTo(111),
                            "depositpaid", equalTo(true),
                            "bookingdates.checkin", equalTo("2018-01-01"),
                            "bookingdates.checkout", equalTo("2019-01-01"),
                            "additionalneeds", equalTo("Breakfast"));

        //2nd Way: Hard Assertion
        JsonPath jsonPath = response.jsonPath();

       assertEquals("James", jsonPath.getString("firstname"));
       assertEquals("Brown", jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
       assertEquals(true, jsonPath.getBoolean("depositpaid"));
       assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
       assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
       assertEquals("Breakfast", jsonPath.getString("additionalneeds"));

       //3rd Way: Soft Assertion

        SoftAssert softAssert = new SoftAssert();

       softAssert.assertEquals(jsonPath.getString("firstname"), "James");
       softAssert.assertEquals(jsonPath.getString("lastname"), "Brown");
       softAssert.assertEquals(jsonPath.getInt("totalprice"), 111);
       softAssert.assertEquals(jsonPath.getBoolean("depositpaid"), true);
       softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01");
       softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01");
       softAssert.assertEquals(jsonPath.getString("additionalneeds"), "Breakfast");

       softAssert.assertAll();









    }










}
