package flutweets.processor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.TreeMap;

public class StateCounter {
  public static TreeMap<String, Integer> count(JSONArray fluTweetsWithLocations){
    TreeMap<String, Integer> counter = new TreeMap<>();
    for (Object each : fluTweetsWithLocations) {
      JSONObject e = (JSONObject) each;
      String state = (String) e.get("state");
      counter.put(state, counter.getOrDefault(state, 0) + 1);
    }
    return counter;
  }
}
