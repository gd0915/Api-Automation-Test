package get_requests_review;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get17 extends HerOkuAppBaseUrl {
     /*
    Given
        "https://restful-booker.herokuapp.com/booking/5 "
    When
      I send a GET request to the Url
   And
       Accept type is "application/json"
   Then
       HTTP Status Code should be 200
   And
       Response format should be "application/json"
   And
       first name should be "Der"
   And
       total price should be 11111
   And
      checkin date should be "2021-11-11"
      {
    "firstname": "Nick",
    "lastname": "Smith",
    "totalprice": 100,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-11-11",
        "checkout": "2022-12-12"
    },
    "additionalneeds": "Breakfast"
}

 */
    @Test
    public void get17(){
        //Set the url
        spec.pathParams("first", "booking", "second", 5);

        //Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-11-11","2022-12-12" );
        BookingPojo expectedData = new BookingPojo("Nick", "Smith", 100, true, bookingDates,"Breakfast" );
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).accept("application/json").when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = JsonUtils.convertJsonToJavaObject(response.asString(), BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(bookingDates.getCheckin(), actualData.getBookingdates().getCheckin());


    }

}
