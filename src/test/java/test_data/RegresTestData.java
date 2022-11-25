package test_data;

import java.util.HashMap;
import java.util.Map;

public class RegresTestData {

    public Map<String, String> dataMapSetUp (String email, String first_name, String last_name, String avatar){
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("email", email);
        dataMap.put("first_name", first_name);
        dataMap.put("last_name", last_name);
        dataMap.put("avatar", avatar);

        return dataMap;
    }
    public Map<String, Object> dataMapSetUp2 (String name, Integer year, String color, String pantone_year){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", name);
        dataMap.put("year", year);
        dataMap.put("color", color);
        dataMap.put("pantone_value", pantone_year);

        return dataMap;
    }

    public Map<String, String> supportMapSetUp (String url, String text){
        Map<String, String> supportMap = new HashMap<>();
        supportMap.put("url", url);
        supportMap.put("text", text);

        return supportMap;
    }

    public Map<Object, Object> expectedDataSetUP(Map<String, String> dataMapSetUp, Map<String, String> supportMapSetUp ){
        Map<Object, Object> expectedData = new HashMap<>();
        expectedData.put("data", dataMapSetUp);
        expectedData.put("support", supportMapSetUp);

        return expectedData;
    }

    public Map<Object, Object> expectedDataSetUP2(Map<String, Object> dataMapSetUp, Map<String, String> supportMapSetUp ){
        Map<Object, Object> expectedData = new HashMap<>();
        expectedData.put("data", dataMapSetUp);
        expectedData.put("support", supportMapSetUp);

        return expectedData;
    }


}

/*
{
    "data": {
        //"id": 3,
        "email": "emma.wong@reqres.in",
        "first_name": "Emma",
        "last_name": "Wong",
        "avatar": "https://reqres.in/img/faces/3-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
 */
