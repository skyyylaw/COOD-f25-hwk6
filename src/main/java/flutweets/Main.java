package flutweets;

import flutweets.processor.FluTweetApp;
import flutweets.ui.UI;

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

    FluTweetApp.run(dataFileName, statesFileName, loggerFileName);
  }
}
