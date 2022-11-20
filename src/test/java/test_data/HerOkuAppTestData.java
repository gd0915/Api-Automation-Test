package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

    public Map<String, String> bookingdatesMapSetUp(String checkin, String checkout){
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", checkin);
        bookingdatesMap.put("checkout", checkout);

        return bookingdatesMap;

    }

    public Map<String, Object> expectedDataSetUp(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdates, String additionalneeds){
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname" ,firstname);
        expectedData.put("lastname" ,lastname);
        expectedData.put("totalprice" ,totalprice);
        expectedData.put("depositpaid" ,depositpaid);
        expectedData.put("bookingdates" ,bookingdates);
        expectedData.put("additionalneeds" ,additionalneeds);

        return expectedData;

    }





}
/*
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
 */
