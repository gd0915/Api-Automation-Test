package get_requests_review;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.AssertJUnit.assertTrue;

public class Get11Review extends GoRestBaseUrl {
     /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Nagabhushanam Chaturvedi", "Nikita Patil", "Maheswar Guha" are among the users
        And
            The female users are less than or equal to male users
     */
    @Test
    public void get11Review(){
        //Set the url
        spec.pathParams("first", "users");

        //Set the expected data



        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion

        response.then().assertThat().statusCode(200).body("meta.pagination.limit", equalTo(10),
                "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data.id", hasSize(10),
                "data.status", hasItem("active"),
                "data.name", hasItems("Nagabhushanam Chaturvedi", "Nikita Patil", "Maheswar Guha"));

        //The female users are less than or equal to male users
        JsonPath jsonPath = response.jsonPath();

        //Groovy Language
        List<String> females = jsonPath.getList("data.findAll{it.gender=='female'}.gender");
        List<String> males = jsonPath.getList("data.findAll{it.gender=='male'}.gender");

        assertTrue(females.size()<= males.size());

        //2nd way:
        List<String> genders = jsonPath.getList("data.gender");
        System.out.println("genders = " + genders);
        int numOfFemales=0;
        for(String w : genders){
            if(w.equals("female")){
                numOfFemales++;
            }
        }
        System.out.println(numOfFemales);
        int numOfMales = genders.size()- numOfFemales;
        assertTrue(females.size()<= males.size());







    }
}
