package flutweets.common;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
  private static volatile Logger instance;
  private final FileWriter writer;

  private Logger(String fileName) throws IOException {
    this.writer = new FileWriter(fileName, false);
  }

  public static Logger getLogger(String fileName) {
    if (instance == null) {
      synchronized (Logger.class) {
        if (instance == null) {
          try {
            instance = new Logger(fileName);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      }
    }
    return instance;
  }

  public synchronized void log(String msg) {
    try {
      writer.write(msg + "\n");
      writer.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized void close() {
    try {
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}