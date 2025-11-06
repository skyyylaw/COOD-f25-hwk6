package flutweets.data;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JsonReader {
  public static JSONArray readTweetsJson(String filename) throws Exception {
    JSONArray jsonArray;
    try {
      Object obj = new JSONParser().parse(new FileReader(filename));
      jsonArray = (JSONArray) obj;
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    // Check for malformed, missing, or wrong type of data
    for (Object obj : jsonArray) {
      JSONObject jsonObject = (JSONObject) obj;
      if (jsonObject.size() < 3 || !jsonObject.containsKey("location") || !jsonObject.containsKey("time") || !jsonObject.containsKey("text")){
        throw new RuntimeException("Malformed or missing data");
      }

      if (!(jsonObject.get("text") instanceof  String) || !(jsonObject.get("time") instanceof  String)){
        throw new RuntimeException("Wrong data type");
      }

      Object coords = jsonObject.get("location");
      if (!(coords instanceof JSONArray)) {
        throw new RuntimeException("Wrong data type");
      }
      JSONArray arr = (JSONArray) coords;
      if (arr.size() != 2 || !(arr.get(0) instanceof Number) || !(arr.get(1) instanceof Number)) {
        throw new RuntimeException("Wrong data type");
      }
    }
    return jsonArray;
  }

  public static JSONArray readStatesJson(String filename) throws Exception {
    JSONArray jsonArray;
    try {
      Object obj = new JSONParser().parse(new FileReader(filename));
      jsonArray = (JSONArray) obj;
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }

    // Check for malformed, missing, or wrong type of data
    for (Object obj : jsonArray) {
      JSONObject jsonObject = (JSONObject) obj;
      if (jsonObject.size() < 3 || !jsonObject.containsKey("name") || !jsonObject.containsKey("latitude") || !jsonObject.containsKey("longitude")){
        throw new RuntimeException("Malformed or missing data");
      }

      Object latObj = jsonObject.get("latitude");
      Object lonObj = jsonObject.get("longitude");
      if (!(jsonObject.get("name") instanceof String) ||
              !(latObj instanceof Number) ||
              !(lonObj instanceof Number)) {
        throw new RuntimeException("Wrong data type");
      }
    }
    return jsonArray;
  }

//  public static void main(String[] args) {
//    JSONArray jsonArray;
//    try {
//      jsonArray = JsonReader.readJsonFile("flu_tweets.json");
//    } catch (Exception e) {
//      System.out.println(e.getMessage());
//      throw new RuntimeException(e);
//    }
////    System.out.println(jsonArray);
//    for (Object obj : jsonArray) {
//      JSONObject jo = (JSONObject) obj;
//      System.out.println(jo.get("text"));
//    }
//  }
}
