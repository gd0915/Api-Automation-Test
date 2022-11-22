package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get12Pojo extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/6838
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                    "firstname": "Sally",
                                    "lastname": "Brown",
                                    "totalprice": 111,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2013-02-23",
                                        "checkout": "2014-10-23"
                                    },
                                    "additionalneeds": "Breakfast"
                        }
     */
    @Test
    public void get12(){
        //Set the url
        spec.pathParams("first", "booking", "second", 6838);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2013-02-23", "2014-10-23");
        System.out.println("bookingDatesPojo = " + bookingDatesPojo);

        BookingPojo expectedData = new BookingPojo("Sally", "Brown", 111, true, bookingDatesPojo, "Breakfast");

        //Send the request get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());

    }
}
