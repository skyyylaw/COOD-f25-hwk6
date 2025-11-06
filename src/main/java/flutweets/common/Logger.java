package flutweets.common;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
  private static Logger instance;
  private FileWriter logger;

  private Logger(String filename) {
    try {
      logger = new FileWriter(filename);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static synchronized Logger getLogger(String filename) {
    if (instance == null) {
      instance = new Logger(filename);
    }
    return instance;
  }

  public synchronized void log(String msg) {
    try {
      logger.write(msg + "\n");
      logger.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}