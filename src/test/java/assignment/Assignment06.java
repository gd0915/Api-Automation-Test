package assignment;

import base_urls.RegresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Assignment06 extends RegresBaseUrl {
    /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
    @Test
    public void assignment06(){
        //Set the url
        spec.pathParam("first", "unknown");

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        response.then().assertThat().statusCode(200);


        JsonPath jsonPath = response.jsonPath();

            // Print all pantone_values
        List<Integer> pantone_values = jsonPath.getList("data.pantone_value");
        System.out.println("pantone_values = " + pantone_values);



        // Print all ids greater than 3 on the console
        //        Assert that there are 3 ids greater than 3

        List<Integer> ids = jsonPath.getList("data.id");
        System.out.println(ids);
        //1st Way:
        List<Integer> idsGreaterThan3 = new ArrayList<>();

        for(int w:ids){
            if(w>3){
                idsGreaterThan3.add(w);
            }
        }
        System.out.println("idsGreaterThan3 =" + idsGreaterThan3);
        assertEquals(3, idsGreaterThan3.size());

            //Print all names whose ids are less than 3 on the console
            //        Assert that the number of names whose ids are less than 3 is 2

        List<String> names = jsonPath.getList("data.name");
        System.out.println("names = " + names);

            List<String> namesWhoseIdLessThan3 = jsonPath.getList("data.findAll{it.id<3}.name");
            System.out.println("namesWhoseIdLessThan3 = " + namesWhoseIdLessThan3);
            assertEquals(2, namesWhoseIdLessThan3.size());



        }

}
