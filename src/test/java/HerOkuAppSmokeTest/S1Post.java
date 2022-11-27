package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class S1Post extends HerOkuAppBaseUrl {

    /*Type an automation smoke test by using "https://restful-booker.herokuapp.com/apidoc/index.html" documentation.
     Create a booking then update, read and delete the booking you created.
    */
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
                "firstname" : "Jim",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
        }
     When
        User sends Post request
     Then
        StatusCode should be 200
     And
        The response body should be like following
        {
                "firstname" : "Jim",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
        }

     */
    static int bookingId;
    @Test
    public void post01(){
      //Set the url
      spec.pathParam("first", "booking");

      //Set the expected data
      BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
      BookingPojo expectedData = new BookingPojo("Jim",  "Brown", 111, true, bookingDates, "Breakfast" );
      System.out.println("expectedData = " + expectedData);

      //Send the request and get the response
      Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
      response.prettyPrint();

      //Do Assertion
      BookingResponsePojo actualData = JsonUtils.convertJsonToJavaObject(response.asString(), BookingResponsePojo.class);
      System.out.println("actualData = " + actualData);

      assertEquals(200, response.statusCode());
      assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
      assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
      assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
      assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
      assertEquals(bookingDates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
      assertEquals(bookingDates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
      assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

      bookingId = actualData.getBookingid();


    }
}
