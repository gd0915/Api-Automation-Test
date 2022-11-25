package get_requests_review;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Review extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/5066
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                "firstname": "john",
                                "lastname": "Brown",
                                "totalprice": 111,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2021-11-11",
                                    "checkout": "2022-12-12"
                                },
                                "additionalneeds": "Breakfast"
}

      */
    @Test
    public void get12Review(){
        //Set the url
        spec.pathParams("first", "booking", "second", 5066);

        //Set the expected data
        BookingDatesPojo bookingdates = new BookingDatesPojo("2021-11-11","2022-12-12");
        BookingPojo expectedData = new BookingPojo("john","Brown", 111, true,bookingdates,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        assertEquals(bookingdates.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingdates.getCheckout(), actualData.getBookingdates().getCheckout());



    }

}
