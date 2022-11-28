package HerOkuAppSmokeTest_copy;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.JsonUtils;


import static HerOkuAppSmokeTest_copy.S1Post.bookingid;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class S2Put extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
            {
            "firstname" : "James",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                 "checkin" : "2022-12-12",
                    "checkout" : "2022-12-28"
            },
            "additionalneeds" : "Breakfast"
        }
      When
           User sends Put request
      Then
            Status Code must be 200
      And
            Response body should be like following
            {
                "firstname" : "James",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2022-12-12",
                    "checkout" : "2022-12-28"
                },
                "additionalneeds" : "Breakfast"
            }
     */
    @Test
    public void put02(){
        //Set the url
        spec.pathParams("first", "booking", "second", bookingid);

        //Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2022-12-12", "2022-12-28");
        BookingPojo expectedData = new BookingPojo("James", "Brown", 111, true, bookingDates, "Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().
                                    spec(spec).
                                    contentType(ContentType.JSON).
                                    headers("Cookie", "token="+generateToken()).
                                    body(expectedData).
                                    when().
                                    put("/{first}/{second}");

        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = JsonUtils.convertJsonToJavaObject(response.asString(), BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

    }
}
