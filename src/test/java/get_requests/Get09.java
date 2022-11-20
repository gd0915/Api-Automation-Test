package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/246
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "Alex",
                "lastname": "Dominguez",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
     */
    @Test
    public void get09a(){
        //Set the url
        spec.pathParams("first", "booking", "second",246);

        //Set the expected data
        //First we create our 'Inner Map'
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2018-01-01");
        bookingdatesMap.put("checkout", "2019-01-01");

        //Now we create 'Outer Map'
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname" ,"Alex");
        expectedData.put("lastname" ,"Dominguez");
        expectedData.put("totalprice" ,111);
        expectedData.put("depositpaid" ,true);
        expectedData.put("bookingdates" ,bookingdatesMap);
        expectedData.put("additionalneeds" ,"Breakfast");

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        //Do de-serialization(converting json data into java language)
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actual data = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

        //1st Way:
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));

        //2nd Way:
        assertEquals(bookingdatesMap.get("checkin"), ((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map)(actualData.get("bookingdates"))).get("checkout"));

    }

    @Test
    public void get09b() {
        //Set the url
        spec.pathParams("first", "booking", "second", 246);

        //Set the expected data
        //First we create our 'Inner Map'
        HerOkuAppTestData obj = new HerOkuAppTestData();
        Map<String, String> bookingdatesMap = obj.bookingdatesMapSetUp("2018-01-01", "2019-01-01");

        Map<String, Object> expectedData = obj.expectedDataSetUp("Alex", "Dominguez", 111, true, bookingdatesMap, "Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        //Do de-serialization(converting json data into java language)
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actual data = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

        //1st Way:
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));

        //2nd Way:
        assertEquals(bookingdatesMap.get("checkin"), ((Map) (actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map) (actualData.get("bookingdates"))).get("checkout"));
    }


}
