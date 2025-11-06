package flutweets.processor;

import flutweets.data.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TweetLocation {

  private static double calculateDistance(double[] point1, double[] point2) {
    return Math.sqrt(Math.pow(point1[0]-point2[0], 2) + Math.pow(point1[1]-point2[1], 2));
  }

  public static JSONArray addLocation(JSONArray tweets, JSONArray states) {
    JSONArray result = new JSONArray();
    for (Object tweetObj : tweets) {
      double minDistance = 1000000000;
      String closestState = "";
      JSONObject tweetJsonObj = (JSONObject) tweetObj;
      for (Object stateObj : states) {
        JSONObject jsonStateObj = (JSONObject) stateObj;
        double[] point1 = {(Double) jsonStateObj.get("latitude"), (Double) jsonStateObj.get("longitude")};
        JSONArray coord = (JSONArray) tweetJsonObj.get("location");
        double[] point2 = {(double) coord.get(0), (double) coord.get(1)};
        double distance = calculateDistance(point1, point2);
        if (distance < minDistance) {
          minDistance = distance;
          closestState = (String) jsonStateObj.get("name");
        }
      }
      tweetJsonObj.put("state", closestState);
      result.add(tweetJsonObj);
    }
    return result;
  }

//  public static void main(String[] args) {
//    try {
//      JSONArray filteredTweets = TweetFilter.fluTweetsfilter(JsonReader.readJsonFile("flu_tweets.json"));
//      JSONArray states = JsonReader.readJsonFile("states.json");
//      JSONArray locationAdded = addLocation(filteredTweets, states);
//      System.out.println(locationAdded);
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
}
