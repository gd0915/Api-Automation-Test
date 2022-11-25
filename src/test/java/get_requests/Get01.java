package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
    1) Postman is used for manual API testing.
    2) We use Rest-Assured Library for Automation API Testing.
    3) To type automation script follow the given steps:
        a) Understand the requirement
        b) Type test cases
            To type test cases we use 'Gherkin Language'
            The keywords are    x) Given: It is for pre-conditions(like URL, EndPoint, body)
                                y) When: It is used for actions (e.g. get request, put request..)
                                z) Then
                                : It is used for outputs. (What do we expect from testing)
                                t) And: It is used for multiple given, when, and then.
        c) Start to type Automation Script
            i)    Set the URL
            ii)   Set the expected Data(The data we will send : Post-Put-Patch)
            iii)  Type code to send the Request
            iv)   Do Assertion (with Junit)


     */

     /*
   Given
       https://restful-booker.herokuapp.com/booking/10
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/

    //Note :We use Test annotation from junit
    //Note : We do everytime first manual testing and check the requirements.
    @Test
    public void get01(){

        //Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/10";

        //Set the expected Data

        //Send the request and get the response
          Response response=given().when().get(url);   // We import given() from RestAssured library, and then we are sending request to the given Url/EndPoint
          response.prettyPrint();                      // We put the request into a Response Interface container
                                                       // We will see the response with the help of prettyPrint() method on the console

        //Do Assertion (Verifying Step)

        //HTTP Status Code should be 200
        //Content Type should be JSON
        //Status Line should be HTTP/1.1 200 OK
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        //NOTE : For the 'Content Type' json ==> We need to type in this following way ==> "application/json"

        //How to print 'Status Code' on the console
        System.out.println("Status Code: "+response.statusCode());

        //How to print a "Content Type" on the console
        System.out.println("Content Type: "+response.contentType());

        //How to print "Status Line" on the console
        System.out.println("Status Line: "+response.statusLine());

        //How to print "Header" on the console
        System.out.println(response.getHeader("Server"));
        System.out.println(response.getHeader("Connection"));

        System.out.println("==================");

        //How to print "Headers" on the console
        System.out.println(response.getHeaders());

        //How to print "Time" on the console(in how much time do I get the response)
        System.out.println(response.getTime());

    }





}
