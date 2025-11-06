package flutweets.processor;

import flutweets.data.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TweetFilter {
  public static JSONArray fluTweetsfilter(JSONArray jsonArray) {
    JSONArray filteredJsonArray = new JSONArray();
    for (Object obj : jsonArray) {
      String text = (String) ((JSONObject) obj).get("text");
      for (String word : text.split(" ")) {
        word = word.toLowerCase();
        if (word.contains("flu")){
          if (word.equals("flu") || word.equals("#flu")) {
            filteredJsonArray.add(obj);
            break;
          }
          if (word.length() > 3 && word.substring(0,3).equals("flu") && (word.charAt(3) < 'a' ||  word.charAt(3) > 'z')) {
            filteredJsonArray.add(obj);
            break;
          }
        }
      }
    }
    return filteredJsonArray;
  }

//  public static void main(String[] args) {
//    try {
//      System.out.println(fluTweetsfilter(JsonReader.readJsonFile("flu_tweets.json")));
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
}
