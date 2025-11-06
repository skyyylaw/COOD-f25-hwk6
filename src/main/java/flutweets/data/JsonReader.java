package flutweets.data;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JsonReader {
  public static JSONArray readJsonFile(String filename) throws Exception {
    JSONArray jsonArray;
    try {
      Object obj = new JSONParser().parse(new FileReader(filename));
      jsonArray = (JSONArray) obj;
    } catch (IOException e) {
//      System.out.println("IO Error - Cannot open JSON file: " + filename);
      throw new RuntimeException(e);
    } catch (ParseException e) {
//      System.out.println("Parse Error - Cannot parse JSON file: " + filename);
      throw new RuntimeException(e);
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
