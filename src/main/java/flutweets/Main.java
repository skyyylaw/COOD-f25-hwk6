package flutweets;

import flutweets.common.Logger;
import flutweets.data.JsonReader;
import flutweets.processor.StateCounter;
import flutweets.processor.TweetFilter;
import flutweets.processor.TweetLocation;
import flutweets.ui.UI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.TreeMap;

public class Main {
  public static void main(String[] args) throws Exception {


    if (args.length != 3) {
      UI.display("Invalid number of runtime arguments: " + args.length);
      return;
    }

    // Get user input from runtime args
    String dataFileName = args[0];
    String statesFileName = args[1];
    String loggerFileName = args[2];

    // Read files and initialize logger
    JSONArray tweets, states;
    try {
      tweets = JsonReader.readJsonFile(dataFileName);
    } catch (Exception e) {
      UI.display("Error reading file: " + dataFileName);
      return;
    }
    try {
      states = JsonReader.readJsonFile(statesFileName);
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

    // Initialize Logger
    Logger logger;
    try {
      logger = Logger.getLogger(loggerFileName);
    } catch (RuntimeException e) {
      UI.display("Error creating log file: " + loggerFileName);
      return;
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
