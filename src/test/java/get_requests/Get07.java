package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {
    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */
    @Test
    public void get07(){

        //Set the Url
        spec.pathParam("first", "todos");

        //Set the expected Data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        //1)Status code is 200
        assertEquals(200, response.statusCode());

        //2)Print all ids greater than 190 on the console(We will take the elements outside the body)
        //   Assert that there are 10 ids greater than 190
        //1st Way:
        JsonPath jsonPath = response.jsonPath();//I cannot get the data directly outside the body, so we use jsonPath object.
        List<Integer> ids = jsonPath.getList("id");//getList(String path)==>("id")==>  It will give us all the id's
        System.out.println("ids =" + ids);//printing ids
        List<Integer> idsGreaterThan190 = new ArrayList<>();//create new empty list to put all ids greater than 190 into it
        for(int w:ids){
            if(w>190){
                idsGreaterThan190.add(w);
            }
        }
        System.out.println("idsGreaterThan190 =" + idsGreaterThan190);

        assertEquals(10, idsGreaterThan190.size());

        //2nd Way:
        List<Integer> idsGreaterThan190Groovy =  jsonPath.getList("findAll{it.id>190}.id");//Groovy Language("it" is like "t->" in lambda expression)
        System.out.println("idsGreaterThan190Groovy = " + idsGreaterThan190Groovy);
        assertEquals(10, idsGreaterThan190Groovy.size());

        // Print all userIds whose ids are less than 5 on the console
        //	   Assert that the number of userIds whose ids are less than 5 is 4

        List<Integer> userIdsLessThan5Groovy = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("UserIdsLessThan5Groovy = " + userIdsLessThan5Groovy);
        assertEquals(4, userIdsLessThan5Groovy.size());

        //4)Print all titles whose ids are less than 5
        //Assert that "delectus aut autem" is one of the titles whose id is less than 5

        List<String> titles = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("titles = " + titles);
        //1st Way:
        assertTrue("\"delectus aut autem\" does not exist", titles.contains("delectus aut autem"));
        //2nd Way:
        assertTrue("'delectus aut autem' does not exist",titles.stream().anyMatch(t->t.equals("delectus aut autem")));

    }


}
