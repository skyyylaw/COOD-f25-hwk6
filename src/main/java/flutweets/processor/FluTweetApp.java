package flutweets.processor;

import flutweets.common.Logger;
import flutweets.data.JsonReader;
import flutweets.ui.UI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.TreeMap;

public class FluTweetApp {

  public static void run(String dataFileName, String statesFileName, String loggerFileName) {
    // Initialize Logger
    Logger logger;
    try {
      logger = Logger.getLogger(loggerFileName);
    } catch (RuntimeException e) {
      UI.display("Error creating log file: " + loggerFileName);
      return;
    }

    // Read files
    JSONArray tweets, states;
    try {
      tweets = JsonReader.readTweetsJson(dataFileName);
    } catch (Exception e) {
      UI.display("Error reading file: " + dataFileName);
      return;
    }
    try {
      states = JsonReader.readStatesJson(statesFileName);
    } catch (Exception e) {
      UI.display("Error reading file: " + statesFileName);
      return;
    }

    // Filter for flu tweets
    JSONArray fluTweets = TweetFilter.fluTweetsfilter(tweets);
    // Add location to each flu tweet
    JSONArray fluTweetsWithLocations = TweetLocation.addLocation(fluTweets, states);
    // Calculate number of flu tweets per state
    TreeMap<String, Integer> counter = StateCounter.count(fluTweetsWithLocations);

    // Display number of flu tweets per state
    for (String state : counter.keySet()) {
      UI.display(state + ": " + counter.get(state));
    }

    // Display number of flu tweets per state
    for (String state : counter.keySet()) {
      UI.display(state + ": " + counter.get(state));
    }


    // Log each flu tweet with state origin
    for (Object obj : fluTweetsWithLocations) {
      JSONObject jsonObject = (JSONObject) obj;
      String message = (String) jsonObject.get("state") + "\t" + (String) jsonObject.get("text");
      try {
        logger.log(message);
      } catch (RuntimeException e) {
        UI.display("Error when loggering this message: " + message);
        return;
      }
    }
    logger.close();
  }


}
